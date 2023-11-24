package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.entities.Restaurante;
import com.kaique.ifood.exception.FormaPagamentoJaExistenteException;

import jakarta.transaction.Transactional;

@Service
public class RestauranteFormaPagamentoService {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private FormaPagamentoService fPService;

	public List<FormaPagamento> ListaFormaPagamento(Long id) {

		Restaurante restaurante = restauranteService.buscaPorId(id);

		return restaurante.getFormaPagamentos();
	}

	@Transactional
	public void deletaFormaPagamentoDeRestaurante(Long restauranteId, Long fP) {
		FormaPagamento formaPagamento = fPService.buscaPorId(fP);
		Restaurante restaurante = restauranteService.buscaPorId(restauranteId);

		restaurante.getFormaPagamentos().remove(formaPagamento);
	}

	@Transactional
	public List<FormaPagamento> addFormaPagamentoEmRestaurante(Long restauranteId, Long fP) {
		FormaPagamento formaPagamento = fPService.buscaPorId(fP);
		Restaurante restaurante = restauranteService.buscaPorId(restauranteId);

		if (restaurante.getFormaPagamentos().contains(formaPagamento))
			throw new FormaPagamentoJaExistenteException();
			
			restaurante.getFormaPagamentos().add(formaPagamento);
		return restaurante.getFormaPagamentos();
	}
}
