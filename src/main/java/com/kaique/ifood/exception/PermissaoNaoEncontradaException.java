package com.kaique.ifood.exception;

public class PermissaoNaoEncontradaException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
	}

	public PermissaoNaoEncontradaException(Long id) {
	this(String.format("ID '%d' de permissoes não pode ser encontrado", id));
	}
}
