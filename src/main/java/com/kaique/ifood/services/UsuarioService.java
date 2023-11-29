package com.kaique.ifood.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.UsuarioDtoConversor;
import com.kaique.ifood.dto.request.AtualizaSenhaDtoRequest;
import com.kaique.ifood.dto.request.AtualizaUsuarioDtoRequest;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.exception.EmailJaExistenteException;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.SenhaInexistenteException;
import com.kaique.ifood.exception.UsuarioNaoEncontradoException;
import com.kaique.ifood.repositories.UsuarioRepositorie;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private UsuarioDtoConversor conversor;

	public List<UsuarioDtoResponce> lista() {
		return conversor.ListaResponce(repositorie.findAll());
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

	@Transactional
	public Usuario atualizaUsuario(AtualizaUsuarioDtoRequest dto, Long id) {

		Optional<Usuario> usuarioExistente = repositorie.findByEmail(dto.getEmail());
		Usuario usuario = this.buscarPorId(id);

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailJaExistenteException();
		}

		conversor.CopiaPropiedadesAtualizacao(dto, usuario);

		return repositorie.save(usuario);
	}

	@Transactional
	public void atualizaSenha(AtualizaSenhaDtoRequest senha, Long id) {

		Usuario usuario = this.buscarPorId(id);
		if (!repositorie.existsBySenha(senha.getSenhaAtual())) {
			throw new SenhaInexistenteException();
		}

		usuario.setSenha(senha.getNovaSenha());
		repositorie.save(usuario);
	}
	

	@Transactional
	public void deleta(Long id) {
		try {
			buscarPorId(id);
			repositorie.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}
}
