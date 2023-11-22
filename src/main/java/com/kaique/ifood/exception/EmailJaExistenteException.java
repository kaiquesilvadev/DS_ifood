package com.kaique.ifood.exception;

import com.kaique.ifood.dto.request.UsuarioDtoRequest;

public class EmailJaExistenteException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EmailJaExistenteException(String msg) {
		super(msg);
	}

	public EmailJaExistenteException(UsuarioDtoRequest usuario) {
		this(String.format("O e-mail '%s' já está em uso. Por favor, corrija o e-mail ou faça o login.",
				usuario.getEmail()));
	}
}
