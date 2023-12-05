package com.kaique.ifood.exception;

public class FormaPagamentoNaoAssociadoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoAssociadoException(String msg) {
		super(msg);
	}

	public FormaPagamentoNaoAssociadoException(Long restauranteId , Long formaPagamentoId) {
		this(String.format("formaPagamento com ID '%d' não encontrada para o restaurante com ID '%d'.", formaPagamentoId, restauranteId));
	}
}
