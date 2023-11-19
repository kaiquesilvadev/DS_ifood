package com.kaique.ifood.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.RestauranteDtoConversor;
import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
import com.kaique.ifood.entities.Restaurante;
import com.kaique.ifood.exception.ChaveEstrangeiraNaoEncontradaException;
import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.RestauranteNaoEncontradaException;
import com.kaique.ifood.repositories.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private RestauranteDtoConversor conversor;

	public List<Restaurante> listar() {
		return repository.findAll();
	}

	public Restaurante buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
	}

	public List<Restaurante> filtraPorTaxas(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	/*
	 * public List<Restaurante> buscaPorNomeEIdDeCozinha(String nome, BigDecimal id)
	 * { return repository.consultarPorNome(nome, id); }
	 */

	public List<Restaurante> buscaRTTPorNomeFrete(String nome, BigDecimal taxaFreteInicia, BigDecimal taxaFreteFinal) {
		return repository.find(nome, taxaFreteInicia, taxaFreteFinal);
	}

	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return repository.findComFreteGratis(nome);
	}

	@Transactional
	public Restaurante adiciona(RestaurantesDtoRequest restauranteDto) {
		try {
			Restaurante restaurante = conversor.converteParaRestaurante(restauranteDto);
			Restaurante novoRestaurante = repository.save(restaurante);
			repository.flush();
			return novoRestaurante;

		} catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
			throw new ChaveEstrangeiraNaoEncontradaException("cozinha", restauranteDto.getCozinha().getId());
		}
	}

	@Transactional
	public Restaurante atualiza(Long id, RestaurantesDtoRequest NovoRestaurante) {
		try {
			Restaurante restauranteAtual = buscaPorId(id);
			conversor.copiaPropiedades(NovoRestaurante, restauranteAtual);
			Restaurante atualizacaoOk = repository.save(restauranteAtual);
			repository.flush();
			return atualizacaoOk;
		} catch (DataIntegrityViolationException e) {
			throw new ChaveEstrangeiraNaoEncontradaException("cozinha", NovoRestaurante.getCozinha().getId());
		}
	}
	
	@Transactional
	public void ativa(Long id) {
		Restaurante ativa = buscaPorId(id);
		ativa.setAtivo(true);
	}
	
	@Transactional
	public void desativa(Long id) {
		Restaurante dersativa = buscaPorId(id);
		dersativa.setAtivo(false);
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
