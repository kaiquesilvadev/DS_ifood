package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.RestauranteProdutoOpenAPI;
import com.kaique.ifood.dto.conversor.ProdutoDtoConversor;
import com.kaique.ifood.dto.request.ProdutoDtoRequest;
import com.kaique.ifood.dto.responce.ProdutoDtoResponce;
import com.kaique.ifood.services.RestauranteProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoOpenAPI{

	@Autowired
	private RestauranteProdutoService service;

	@Autowired
	private ProdutoDtoConversor conversor;

	@Override
	@GetMapping
	public List<ProdutoDtoResponce> listaProdutos(@PathVariable Long restauranteId) {
		return conversor.ListDtoProduto(service.lista(restauranteId));
	}

	@Override
	@GetMapping("/{produtoId}")
	public ProdutoDtoResponce buscaIdDeProdutoEmRestaurante(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return conversor.converteProduto(service.buscaIdEmRestaurante(restauranteId, produtoId));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProdutoDtoResponce adiciona(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoDtoRequest dtoRequest) {
		return conversor.converteProduto(service.adiciona(dtoRequest, restauranteId));
	}

	@Override
	@PutMapping("/{produtoId}")
	public ProdutoDtoResponce atualizar(@Valid @RequestBody ProdutoDtoRequest dtoRequest , @PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return conversor.converteProduto(service.atualizar(dtoRequest, restauranteId, produtoId));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{produtoId}/ativa")
	public void ativaProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		service.ativarProduto(restauranteId, produtoId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{produtoId}/desativa")
	public void desativaProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		service.desativaProduto(restauranteId, produtoId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{produtoId}")
	public void deletaProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		service.deletaProduto(restauranteId, produtoId);
	}


}
