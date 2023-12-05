package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kaique.ifood.dto.referencias.RestauranteDtoRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDtoResponce {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataContirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;
	private EnderecoDtoResponce enderecoEntrega;
	private FormaPagamentoDtoResponce formaPagamento;
	private RestauranteDtoRef restaurante;
	private UsuarioResumoDtoResponce usuarioCliente;
	private List<ItemPedidoDtoResponce> itens = new ArrayList<>();

}
