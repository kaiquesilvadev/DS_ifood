package com.kaique.ifood.dto.request;

import com.kaique.ifood.dto.referencias.EstadoDtoRef;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDtoRequest {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoDtoRef estado;
}
