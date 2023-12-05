package com.kaique.ifood.dto.referencias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDtoRef {

	@NotNull
	private Long id;
	private String nome;
	private String cozinhaNome;
}
