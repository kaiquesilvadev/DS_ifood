package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService service;

	@GetMapping
	public List<Usuario> list() {
		return service.lista();
	}

	@GetMapping("/{id}")
	public Usuario buscaPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
}
