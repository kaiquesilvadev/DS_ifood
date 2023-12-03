package com.kaique.ifood.dto.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDtoResponce {
	
	private String Cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidadeNome;
}
