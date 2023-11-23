package com.kaique.ifood.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizaUsuarioDtoRequest {

	@NotBlank
	private String nome;
	
	@Email
	private String email;
}
