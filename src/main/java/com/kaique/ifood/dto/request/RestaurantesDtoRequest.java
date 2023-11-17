package com.kaique.ifood.dto.request;

import java.math.BigDecimal;

import com.kaique.ifood.core.validation.TaxaFrete;
import com.kaique.ifood.dto.referencias.CozinhaDtoRef;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantesDtoRequest {

	@NotBlank
	private String nome;

	//@PositiveOrZero o valor que o usuário digitar tem quer igual ou maio que zero 
	@TaxaFrete // A anotação @TaxaFrete foi criada com propósitos didáticos e tem o mesmo efeito prático que a anotação @PositiveOrZero. 
	@DecimalMin("0")
	private BigDecimal taxaFrete;

 	@NotNull
	private CozinhaDtoRef cozinha;
}
