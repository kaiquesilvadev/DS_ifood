package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.EstadoDtoConversor;
import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.entities.Estado;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.EstadoNaoEncontradaException;
import com.kaique.ifood.repositories.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	@Autowired
	private EstadoDtoConversor conversor;

	public List<Estado> listar() {
		return repository.findAll();
	}

	public Estado buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradaException(id));
	}

	@Transactional
	public Estado adiciona(EstadoDtoRequest estadoDto) {
		Estado novoEstado = conversor.conversorDto(estadoDto);
		return repository.save(novoEstado);
	}

	@Transactional
	public Estado atualiza(Long id, EstadoDtoRequest estadoDto) {

		Estado estadoAtual = buscaPorId(id);
		conversor.copiaPropiedades(estadoDto, estadoAtual);
		return repository.save(estadoAtual);
	}

	@Transactional
	public void deletar(Long id) {
		try {
			buscaPorId(id);
			repository.deleteById(id);
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}

	}
}
