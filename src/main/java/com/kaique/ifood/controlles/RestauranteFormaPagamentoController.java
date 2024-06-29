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

import com.kaique.ifood.documentation.RestauranteFormaPagamentoOpenAPI;
import com.kaique.ifood.dto.conversor.FormaPagamentoDtoConversor;
import com.kaique.ifood.dto.responce.FormaPagamentoDtoResponce;
import com.kaique.ifood.services.RestauranteFormaPagamentoService;

@RestController
@RequestMapping("/restaurante/{restauranteId}/formaPagamentos")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoOpenAPI{

	@Autowired
	private RestauranteFormaPagamentoService serviceFP;
	
	@Autowired
	private FormaPagamentoDtoConversor conversorFP;

	@Override
	@GetMapping
	public List<FormaPagamentoDtoResponce> listaFormaPagamento(@PathVariable Long restauranteId) {
		return conversorFP.listaDto(serviceFP.ListaFormaPagamento(restauranteId));
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{formaPagamentoId}")
	public void deletaFormaPagamentoDeRestaurante(@PathVariable Long restauranteId ,@PathVariable Long formaPagamentoId) {
		serviceFP.deletaFormaPagamentoDeRestaurante(restauranteId, formaPagamentoId);
	}
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{formaPagamentoId}")
	public List<FormaPagamentoDtoResponce> addFormaPagamentoEmRestaurante(@PathVariable Long restauranteId ,@PathVariable Long formaPagamentoId) {
		return conversorFP.listaDto(serviceFP.addFormaPagamentoEmRestaurante(restauranteId, formaPagamentoId));
	}
}
