package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoDtoResponce {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private Boolean ativo;
	private EnderecoDtoResponce endereco;
    private CozinhaDtoResponce cozinha;
}
