package com.kaique.ifood.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Estado;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.EntidadeNaoEncontradaException;
import com.kaique.ifood.repositories.EstadoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public List<Estado> listar() {
		return repository.findAll();
	}

	public Optional<Estado> buscaPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public Estado adiciona(Estado estado) throws ConstraintViolationException {
		return repository.save(estado);
	}

	@Transactional
	public Estado atualiza(Long id, Estado NovoEstado) throws ConstraintViolationException {
		Estado estadoAtual = repository.findById(id).get();
		BeanUtils.copyProperties(NovoEstado, estadoAtual, "id");
		return repository.save(estadoAtual);
	}

	@Transactional
	public void deletar(Long id) {
		try {
			if (repository.findById(id).isEmpty())
				throw new EntidadeNaoEncontradaException(String.format("Código %d não encontrado ", id));
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido , pois está em uso ", id));
		}

	}
}