package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.kaique.ifood.dto.referencias.RestauranteDtoRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDtoResponce extends RepresentationModel<PedidoResumoDtoResponce> {

	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteDtoRef restaurante;
	private UsuarioResumoDtoResponce cliente;	
}
