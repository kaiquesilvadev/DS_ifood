package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDtoResponce extends RepresentationModel<ItemPedidoDtoResponce> {

	private Long id;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	private Long produtoId;
	
}
