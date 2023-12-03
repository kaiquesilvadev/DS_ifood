package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.kaique.ifood.dto.referencias.RestauranteDtoRef;
import com.kaique.ifood.entities.Endereco;
import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.enuns.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDtoResponce {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteDtoRef restaurante;
	private UsuarioDtoResponce cliente;	
}
