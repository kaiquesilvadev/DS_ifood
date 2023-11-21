package com.kaique.ifood.exception;

public class GrupoNaoEncontradoException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public GrupoNaoEncontradoException(Long id) {
		this(String.format("Não foi possível encontrar o ID de código '%d' no grupo. Verifique se o ID está correto e tente novamente. ", id));
	}

}
