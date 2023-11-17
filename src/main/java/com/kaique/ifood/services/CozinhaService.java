package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.CozinhaDtoConversor;
import com.kaique.ifood.dto.request.CozinhaDtoRequest;
import com.kaique.ifood.entities.Cozinha;
import com.kaique.ifood.exception.CozinhaNaoEncontradaException;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.repositories.CozinhaRepository;

import jakarta.transaction.Transactional;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private CozinhaDtoConversor Conversor;

	public List<Cozinha> listar() {
		return repository.findAll();
	}

	public Cozinha buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

	public List<Cozinha> buscarPorNome(String nome) {
		return repository.findByNomeContainsIgnoreCase(nome);
	}

	@Transactional
	public Cozinha adiciona(CozinhaDtoRequest Cozinha) {
		Cozinha novaCozinha = Conversor.converteDtopara(Cozinha);
		return repository.save(novaCozinha);
	}

	@Transactional
	public Cozinha atualiza(Long id, CozinhaDtoRequest NovaCozinha) {
		Cozinha CozinhaAtual = buscaPorId(id);
		Conversor.copiaPropiedades(NovaCozinha, CozinhaAtual);
		return repository.save(CozinhaAtual);
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