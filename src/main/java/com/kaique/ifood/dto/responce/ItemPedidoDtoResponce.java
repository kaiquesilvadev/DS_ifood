package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.entities.Produto;

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
	private Produto produto;
	private Pedido pedido;
}
