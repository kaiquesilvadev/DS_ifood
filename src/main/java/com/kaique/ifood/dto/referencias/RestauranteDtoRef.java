package com.kaique.ifood.dto.referencias;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDtoRef extends RepresentationModel<RestauranteDtoRef> {

	@NotNull
	private Long id;
	private String nome;
	private String cozinhaNome;
}
