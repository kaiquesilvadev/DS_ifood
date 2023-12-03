package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;

import com.kaique.ifood.dto.referencias.PedidoDtoRef;
import com.kaique.ifood.dto.referencias.ProdutoDtoRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDtoResponce {

	private Long id;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	private Long produtoId;
	
}
