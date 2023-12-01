package com.kaique.ifood.exception;

public class PedidoNaoEncontradoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}

	public PedidoNaoEncontradoException(Long id) {
		this(String.format("ID de código %d , não encontrado", id));
	}
}
