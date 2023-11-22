package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.exception.UsuarioNaoEncontradoException;
import com.kaique.ifood.repositories.UsuarioRepositorie;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorie repositorie;
	
	public List<Usuario> lista() {
		return repositorie.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return repositorie.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException(id));
	}
}
