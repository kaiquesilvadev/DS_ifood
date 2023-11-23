package com.kaique.ifood.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizaSenhaDtoRequest {

	@NotBlank
	private String senhaAtual;
	
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).*$")
	@NotNull
	@Size(min = 6 , max = 15)
	private String NovaSenha;
}
