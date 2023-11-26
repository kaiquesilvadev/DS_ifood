package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Produto;
import com.kaique.ifood.entities.Restaurante;

@Service
public class RestauranteProdutoService {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	
	public List<Produto> lista(Long restauranteId) {
		Restaurante restaurante = restauranteService.buscaPorId(restauranteId);
		return restaurante.getProdutos();
	}
	
	public Produto buscaIdEmRestaurante(Long restauranteId , Long ProdutoId) {
		restauranteService.buscaPorId(restauranteId);
		return  produtoService.buscaIdEmRestaurante(restauranteId , ProdutoId);
	}
}
