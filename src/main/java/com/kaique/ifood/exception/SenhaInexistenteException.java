package com.kaique.ifood.exception;

public class SenhaInexistenteException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public SenhaInexistenteException(String msg) {
		super(msg);
	}

	public SenhaInexistenteException() {
		this("Falha ao atualizar a senha. A senha antiga fornecida está incorreta.");
	}

}
