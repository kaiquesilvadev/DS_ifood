package com.kaique.ifood.dto.request;

import com.kaique.ifood.dto.referencias.ProdutoDtoRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDtoRequest {
	
	private Integer quantidade;
	private String observacao;
	private ProdutoDtoRef produto;
}