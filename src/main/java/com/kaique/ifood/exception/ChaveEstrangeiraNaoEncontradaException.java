package com.kaique.ifood.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;

public class ChaveEstrangeiraNaoEncontradaException extends NegocioException{
	private static final long serialVersionUID = 1L;
	
	public ChaveEstrangeiraNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ChaveEstrangeiraNaoEncontradaException(DataIntegrityViolationException e) {
		this(String.format("Não foi possível encontrar a chave estrangeira de '%s'.", extrairNomeDaPropriedade(e)));
	}

	public ChaveEstrangeiraNaoEncontradaException(String nome , Long id) {
		this(String.format("Não foi possível encontrar a chave estrangeira de '%s', ID %d.", nome, id));
	}
	
	private static String extrairNomeDaPropriedade(DataIntegrityViolationException e) {
		Throwable causaMaisEspecifica = ExceptionUtils.getRootCause(e);

		if (causaMaisEspecifica != null) {
			String mensagemErro = causaMaisEspecifica.getMessage();

			Pattern pattern = Pattern.compile("FOREIGN KEY \\(`(\\w+)`\\)");
			Matcher matcher = pattern.matcher(mensagemErro);

			if (matcher.find()) {
				return matcher.group(1);
			}
		}

		return "propriedadeDesconhecida";
	}
}
