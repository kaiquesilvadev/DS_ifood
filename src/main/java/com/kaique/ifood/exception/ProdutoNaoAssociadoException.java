package com.kaique.ifood.exception;

public class ProdutoNaoAssociadoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public ProdutoNaoAssociadoException(String msg) {
		super(msg);
	}

	public ProdutoNaoAssociadoException(Long restauranteId , Long produtoId) {
		this(String.format("Produto com ID '%d' não encontrado para o restaurante com ID '%d'.", produtoId, restauranteId));
	}
}
