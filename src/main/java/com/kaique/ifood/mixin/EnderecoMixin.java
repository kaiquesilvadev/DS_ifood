package com.kaique.ifood.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kaique.ifood.entities.Cidade;

public class EnderecoMixin {

	
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private Cidade cidade;
}
