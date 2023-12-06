package com.kaique.ifood.exception;

import com.kaique.ifood.enuns.StatusPedido;

public class ViolacaoStatusPedidoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public ViolacaoStatusPedidoException(String msg) {
		super(msg);
	}
	
	public ViolacaoStatusPedidoException(StatusPedido atual , StatusPedido troca ) {
		this(String.format("Não é possível alterar o status '%s' para '%s'.",  atual, troca));
	}
}
