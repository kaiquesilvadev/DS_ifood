package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.ProdutoDtoConversor;
import com.kaique.ifood.dto.request.ProdutoDtoRequest;
import com.kaique.ifood.dto.responce.ProdutoDtoResponce;
import com.kaique.ifood.services.RestauranteProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private RestauranteProdutoService service;

	@Autowired
	private ProdutoDtoConversor conversor;

	@GetMapping
	public List<ProdutoDtoResponce> listaProdutos(@PathVariable Long restauranteId) {
		return conversor.ListDtoProduto(service.lista(restauranteId));
	}

	@GetMapping("/{produtoId}")
	public ProdutoDtoResponce buscaidEmRestaurante(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return conversor.converteProduto(service.buscaIdEmRestaurante(restauranteId, produtoId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProdutoDtoResponce buscaidEmRestaurante(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoDtoRequest dtoRequest) {
		return conversor.converteProduto(service.adiciona(dtoRequest, restauranteId));
	}
}
