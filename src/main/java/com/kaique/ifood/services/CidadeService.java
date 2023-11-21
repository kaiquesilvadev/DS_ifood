package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaique.ifood.dto.conversor.CidadeDtoConversor;
import com.kaique.ifood.dto.request.CidadeDtoRequest;
import com.kaique.ifood.entities.Cidade;
import com.kaique.ifood.exception.ChaveEstrangeiraNaoEncontradaException;
import com.kaique.ifood.exception.CidadeNaoEncontradaException;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.repositories.CidadeRepository;
import com.kaique.ifood.repositories.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	@Autowired
	private EstadoRepository estado;

	@Autowired
	private CidadeDtoConversor conversor;

	public List<Cidade> listar() {
		return repository.findAll();
	}

	public Cidade buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

	@Transactional
	public Cidade adiciona(CidadeDtoRequest cidadeDto) {

		Cidade novaCidade = conversor.converteDto(cidadeDto);

		estado.findById(novaCidade.getEstado().getId()).orElseThrow(
				() -> new ChaveEstrangeiraNaoEncontradaException("Estado", novaCidade.getEstado().getId()));

		return repository.save(novaCidade);
	}

	@Transactional
	public Cidade atualiza(Long id, CidadeDtoRequest cidadeDto) {

		estado.findById(cidadeDto.getEstado().getId())
				.orElseThrow(() -> new ChaveEstrangeiraNaoEncontradaException("Estado", cidadeDto.getEstado().getId()));

		Cidade cidadeAtual = buscaPorId(id);
		conversor.copiaPropiedades(cidadeDto, cidadeAtual);
		return repository.save(cidadeAtual);
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
