package com.kaique.ifood.exception;

import com.kaique.ifood.dto.request.UsuarioDtoRequest;

public class EmailJaExistenteException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EmailJaExistenteException(String msg) {
		super(msg);
	}
	
	public EmailJaExistenteException() {
		this("O e-mail fornecido já está associado a uma conta. Por favor, verifique o e-mail inserido ou faça o login caso já possua uma conta.");
	}

	public EmailJaExistenteException(UsuarioDtoRequest usuario) {
		this(String.format("O e-mail '%s' já está em uso. Por favor, corrija o e-mail ou faça o login.",
				usuario.getEmail()));
	}
}
