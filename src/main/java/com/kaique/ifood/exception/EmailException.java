package com.kaique.ifood.exception;

public class EmailException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmailException(String msg) {
		super(msg);
	}
}