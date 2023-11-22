package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<Usuario> list() {
		return service.lista();
	}

	@GetMapping("/{id}")
	public Usuario buscaPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario adiciona(@Valid @RequestBody UsuarioDtoRequest dtoRequest ) {
		return service.adiciona(dtoRequest);
	}
}
