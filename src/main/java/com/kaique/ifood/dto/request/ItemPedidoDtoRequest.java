package com.kaique.ifood.dto.request;

import com.kaique.ifood.dto.referencias.ProdutoDtoRef;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDtoRequest {
	
	@NotNull
	@Positive
	private Integer quantidade;
	private String observacao;
	
	@Valid
	@NonNull
	private ProdutoDtoRef produto;
}