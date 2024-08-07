package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.CozinhaOpenAPI;
import com.kaique.ifood.dto.conversor.CozinhaDtoConversor;
import com.kaique.ifood.dto.request.CozinhaDtoRequest;
import com.kaique.ifood.dto.responce.CozinhaDtoResponce;
import com.kaique.ifood.services.CozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/cozinhas")
public class CozinhaController implements CozinhaOpenAPI{

	@Autowired
	private CozinhaService service;
	
	@Autowired
	private CozinhaDtoConversor Conversor;

	@Override
	@GetMapping
	public List<CozinhaDtoResponce> listar() {
		return Conversor.listaDto(service.listar());
	}

	/*
	 * esse método foi criado com o intuído de praticar o exercício do curso , mais
	 * não a a necessidade de usar o "produces" a não ser que você queira que a api
	 * retorne um formato especifico no endpoint
	 * 
	 * @GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE) public
	 * ResponseEntity<List<Cozinha>> listar() { return
	 * ResponseEntity.status(HttpStatus.OK).body(service.listar()); }
	 */

	@Override
	@GetMapping("/{id}")
	public CozinhaDtoResponce buscaPorId(@PathVariable Long id) {
		return Conversor.converteEntity(service.buscaPorId(id));
	}

	@Override
	@GetMapping("/buscarPorNome/{nome}")
	public List<CozinhaDtoResponce> buscarPorNome(String nome) {
		return Conversor.listaDto(service.buscarPorNome(nome));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CozinhaDtoResponce adiciona(@Valid @RequestBody CozinhaDtoRequest cozinha) {
		return Conversor.converteEntity(service.adiciona(cozinha));
	}

	@Override
	@PutMapping("/{cozinhaId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public CozinhaDtoResponce atualiza(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaDtoRequest cozinha) {
		return Conversor.converteEntity(service.atualiza(cozinhaId, cozinha));
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
