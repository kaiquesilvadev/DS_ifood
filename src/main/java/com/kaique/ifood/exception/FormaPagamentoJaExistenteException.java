package com.kaique.ifood.exception;

public class FormaPagamentoJaExistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public FormaPagamentoJaExistenteException(String msg) {
		super(msg);
	}
	
	public FormaPagamentoJaExistenteException() {
		this("Esta opção de pagamento já está disponível em restaurantes. ");
	}
}
