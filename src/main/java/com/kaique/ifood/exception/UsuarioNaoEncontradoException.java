package com.kaique.ifood.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}

	public UsuarioNaoEncontradoException(Long id) {
		this(String.format("Não foi possível encontrar o ID de código '%d' em. Verifique se o ID está correto e tente novamente. ", id));
	}
	
}
