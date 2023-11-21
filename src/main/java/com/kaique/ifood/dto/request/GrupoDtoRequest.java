package com.kaique.ifood.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoDtoRequest {

	@NotBlank
	private String nome;
}
