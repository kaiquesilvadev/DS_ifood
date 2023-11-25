package com.kaique.ifood.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDtoRequest {

	@NotBlank
	private String nome;
	private String descricao;

	@NotNull
	private BigDecimal preco;
}
