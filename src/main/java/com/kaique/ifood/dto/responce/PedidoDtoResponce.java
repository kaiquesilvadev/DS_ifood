package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.kaique.ifood.dto.referencias.RestauranteDtoRef;
import com.kaique.ifood.entities.Endereco;
import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.enuns.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDtoResponce {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido statusPedido;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataContirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;
	private Endereco enderecoEntrega;
	private FormaPagamento formaPagamento;
	private RestauranteDtoRef restaurante;
	private UsuarioDtoResponce cliente;

	
}
