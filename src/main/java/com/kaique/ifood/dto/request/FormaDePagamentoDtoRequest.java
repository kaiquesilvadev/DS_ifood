package com.kaique.ifood.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoDtoRequest {

	@NotBlank
	private String descricao;
}
