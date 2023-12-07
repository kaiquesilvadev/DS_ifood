package com.kaique.ifood.exception;

public class PedidoNaoEncontradoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String codigo) {
		super(String.format("Não foi possível encontrar um registro com o código %s.", codigo));

	}
}
