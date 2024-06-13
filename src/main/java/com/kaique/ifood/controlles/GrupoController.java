package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.GrupoDtoConversor;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.services.GrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService service;
	
	@Autowired
	private GrupoDtoConversor conversor;
	
	@GetMapping
	public List<Grupo> List() {
		return service.lista();
	}
	
	@GetMapping("/{id}")
	public Grupo buscaPorId(@PathVariable Long id) {
		return service.buscaPorId(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Grupo adiciona(@Valid @RequestBody GrupoDtoRequest dto) {
		return service.adiciona(dto);
	}
	
	@PostMapping("/{grupoId}/permissoes/{permissaoId}")
	public Grupo atualiza(@PathVariable Long grupoId , @PathVariable Long permissaoId) {
		return service.referencia(grupoId, permissaoId);
	}
	
	@PutMapping("/{id}")
	public Grupo atualiza(@Valid @RequestBody GrupoDtoRequest dto , @PathVariable Long id) {
		return service.atualiza(dto, id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleta(@PathVariable Long id) {
		service.deleta(id);
	}
	
	@DeleteMapping("/{grupoId}/permissoes/{permissaoId}")
	public Grupo removerPermissao(@PathVariable Long grupoId , @PathVariable Long permissaoId) {
		return service.removerPermissao(grupoId, permissaoId);
	}
}
