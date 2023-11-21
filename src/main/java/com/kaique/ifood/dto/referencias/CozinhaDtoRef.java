package com.kaique.ifood.dto.referencias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDtoRef {
	
	@NotNull
	private Long id;
}
