package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Produto;
import com.kaique.ifood.entities.Restaurante;
import com.kaique.ifood.repositories.ProdutoRepositorie;

@Service
public class RestauranteProdutoService {

	@Autowired
	private ProdutoRepositorie produtoRepositorie;
	
	@Autowired
	private RestauranteService restauranteService;
	
	
	public List<Produto> lista(Long restauranteId) {
		Restaurante restaurante = restauranteService.buscaPorId(restauranteId);
		return restaurante.getProdutos();
	}
}
