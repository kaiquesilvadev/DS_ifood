package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.FormaPagamentoDtoConversor;
import com.kaique.ifood.dto.request.FormaPagamentoDtoRequest;
import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.FormaPagamentoNaoEncontradaException;
import com.kaique.ifood.repositories.FormaPagamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	@Autowired
	private FormaPagamentoDtoConversor conversor;

	public List<FormaPagamento> lista() {
		return repository.findAll();
	}

	public FormaPagamento buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}

	@Transactional
	public FormaPagamento adiciona(FormaPagamentoDtoRequest dtoFP) {
		return repository.save(conversor.ConversorDto(dtoFP));
	}

	@Transactional
	public FormaPagamento atualiza(FormaPagamentoDtoRequest dto, Long id) {

		FormaPagamento fp = this.buscaPorId(id);
		conversor.CopiaPropiedades(dto, fp);
		return repository.save(fp);
	}

	public void deletar(Long id) {
		try {
			this.buscaPorId(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}
}
