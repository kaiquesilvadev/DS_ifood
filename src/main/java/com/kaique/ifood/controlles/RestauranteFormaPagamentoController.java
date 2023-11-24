package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.services.RestauranteFormaPagamentoService;

@RestController
@RequestMapping("/restaurante/{restauranteId}/formaPagamentos")
public class RestauranteFormaPagamentoController {

	@Autowired
	private RestauranteFormaPagamentoService serviceFP;
	
	@GetMapping
	public List<FormaPagamento> listaFormaPagamento(@PathVariable Long restauranteId) {
		return serviceFP.ListaFormaPagamento(restauranteId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{formaPagamentoId}")
	public void deletaFormaPagamentoDeRestaurante(@PathVariable Long restauranteId ,@PathVariable Long formaPagamentoId) {
		serviceFP.deletaFormaPagamentoDeRestaurante(restauranteId, formaPagamentoId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{formaPagamentoId}")
	public List<FormaPagamento> addFormaPagamentoEmRestaurante(@PathVariable Long restauranteId ,@PathVariable Long formaPagamentoId) {
		return serviceFP.addFormaPagamentoEmRestaurante(restauranteId, formaPagamentoId);
	}
}
