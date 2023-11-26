package com.kaique.ifood.exception;

public class ProdutoNaoEncontradoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProdutoNaoEncontradoException(Long id) {
		this(String.format("Produto com o ID %s não encontrado.", id));
	}
}
