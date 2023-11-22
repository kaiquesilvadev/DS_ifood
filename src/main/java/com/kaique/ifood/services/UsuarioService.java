package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.UsuarioDtoConversor;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.exception.EmailJaExistenteException;
import com.kaique.ifood.exception.UsuarioNaoEncontradoException;
import com.kaique.ifood.repositories.UsuarioRepositorie;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private UsuarioDtoConversor conversor;

	public List<Usuario> lista() {
		return repositorie.findAll();
	}

	public Usuario buscarPorId(Long id) {
		return repositorie.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Transactional
	public Usuario adiciona(UsuarioDtoRequest dto) {

		if (repositorie.existsByEmail(dto.getEmail())) {
			throw new EmailJaExistenteException(dto);
			
		}
		return repositorie.save(conversor.converteDto(dto));
	}
}
