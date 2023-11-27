package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.PermissaodtoConversor;
import com.kaique.ifood.dto.request.PermissaoDtoRequest;
import com.kaique.ifood.entities.Permissao;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.PermissaoNaoEncontradaException;
import com.kaique.ifood.repositories.PermissaoRepository;

import jakarta.transaction.Transactional;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository repository;

	@Autowired
	private PermissaodtoConversor conversor;

	public List<Permissao> lista() {
		return repository.findAll();
	}

	public Permissao buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}

	@Transactional
	public Permissao adiciona(PermissaoDtoRequest dtoRequest) {
		Permissao permissao = conversor.converDto(dtoRequest);
		return repository.save(permissao);
	}

	@Transactional
	public Permissao atualiza(PermissaoDtoRequest dtoRequest, Long id) {
		Permissao permissao = this.buscaPorId(id);
		conversor.copiaPropiedades(dtoRequest, permissao);
		return repository.save(permissao);
	}

	@Transactional
	public void deleta(Long id) {
		try {
			this.buscaPorId(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
		
	}
}
