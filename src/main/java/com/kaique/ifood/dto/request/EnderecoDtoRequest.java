package com.kaique.ifood.dto.request;

import org.aspectj.weaver.ast.Not;

import com.kaique.ifood.dto.referencias.CidadeDtoRef;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDtoRequest {

	@NotBlank
	private String Cep;

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;
	private String complemento;

	@NotBlank
	private String bairro;

	@Valid
	@NotNull
	private CidadeDtoRef cidade;
}
