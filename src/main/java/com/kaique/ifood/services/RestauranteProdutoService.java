package com.kaique.ifood.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.ProdutoDtoConversor;
import com.kaique.ifood.dto.request.ProdutoDtoRequest;
import com.kaique.ifood.entities.Produto;
import com.kaique.ifood.entities.Restaurante;

import jakarta.transaction.Transactional;

@Service
public class RestauranteProdutoService {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private ProdutoDtoConversor conversor;

	@Transactional
	public List<Produto> lista(Long restauranteId) {
		Restaurante restaurante = restauranteService.buscaPorId(restauranteId);
		Hibernate.initialize(restaurante.getProdutos());
		return restaurante.getProdutos();
	}

	public Produto buscaIdEmRestaurante(Long restauranteId, Long ProdutoId) {
		restauranteService.buscaPorId(restauranteId);
		return produtoService.buscaIdEmRestaurante(restauranteId, ProdutoId);
	}

	@Transactional
	public Produto adiciona(ProdutoDtoRequest dtoRequest, Long RestauranteId) {
		Restaurante restaurante = restauranteService.buscaPorId(RestauranteId);
		Produto produto = conversor.converteDto(dtoRequest, restaurante);
		return produtoService.salva(produto);
	}
	
	@Transactional
	public Produto atualizar(ProdutoDtoRequest dtoRequest, Long restauranteId , Long produtoId) {
		restauranteService.buscaPorId(restauranteId);
		Produto produto = produtoService.buscaIdEmRestaurante(restauranteId, produtoId);
		conversor.copiaPropiedades(dtoRequest, produto);
		return produtoService.salva(produto);
	}
	
	@Transactional
	public void ativarProduto(Long restauranteId , Long produtoid) {
		restauranteService.buscaPorId(restauranteId);
		Produto produto = produtoService.buscaIdEmRestaurante(restauranteId, produtoid);
		produto.setAtivo(true);
	}
	
	@Transactional
	public void desativaProduto(Long restauranteId , Long produtoid) {
		restauranteService.buscaPorId(restauranteId);
		Produto produto = produtoService.buscaIdEmRestaurante(restauranteId, produtoid);
		produto.setAtivo(false);
	}
	
	public void deletaProduto (Long restauranteId , Long produtoid) {
		restauranteService.buscaPorId(restauranteId);
		produtoService.buscaIdEmRestaurante(restauranteId, produtoid);
		produtoService.deleta(produtoid);
	}
}
