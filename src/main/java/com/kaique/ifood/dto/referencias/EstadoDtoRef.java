package com.kaique.ifood.dto.referencias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDtoRef {

	@NotNull
	private Long id;
}
