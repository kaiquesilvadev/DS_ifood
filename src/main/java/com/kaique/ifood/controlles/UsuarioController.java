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

import com.kaique.ifood.documentation.UsuarioOpenAPI;
import com.kaique.ifood.dto.conversor.UsuarioDtoConversor;
import com.kaique.ifood.dto.request.AtualizaSenhaDtoRequest;
import com.kaique.ifood.dto.request.AtualizaUsuarioDtoRequest;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController implements UsuarioOpenAPI{

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioDtoConversor conversor;

	@Override
	@GetMapping
	public List<UsuarioDtoResponce> list() {
		return service.lista();
	}

	@Override
	@GetMapping("/{id}")
	public UsuarioDtoResponce buscaPorId(@PathVariable Long id) {
		return conversor.converteUsuario(service.buscarPorId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UsuarioDtoResponce adiciona(@Valid @RequestBody UsuarioDtoRequest dtoRequest) {
		return conversor.converteUsuario(service.adiciona(dtoRequest));
	}

	@Override
	@PutMapping("{id}")
	public UsuarioDtoResponce atualizaUsuario(@Valid @RequestBody AtualizaUsuarioDtoRequest dtoRequest,
			@PathVariable Long id) {
		return conversor.converteUsuario(service.atualizaUsuario(dtoRequest, id));
	}

	@Override
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PutMapping("{id}/atualizar-senha")
	public void atualizaSenha(@Valid @RequestBody AtualizaSenhaDtoRequest dtoRequest, @PathVariable Long id) {
		service.atualizaSenha(dtoRequest, id);
	}

	@Override
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Long id) {
		service.deleta(id);
	}
}
