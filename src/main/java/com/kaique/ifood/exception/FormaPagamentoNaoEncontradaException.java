package com.kaique.ifood.exception;

public class FormaPagamentoNaoEncontradaException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String msg) {
		super(msg);
	}

	public FormaPagamentoNaoEncontradaException(Long id) {
		this(String.format("ID de código '%d' não foi localizada. Verifique se a ID está correta e tente novamente.", id));
	}
}
