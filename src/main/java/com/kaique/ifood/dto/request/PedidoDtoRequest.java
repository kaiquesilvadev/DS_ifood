package com.kaique.ifood.dto.request;

import java.util.List;

import com.kaique.ifood.dto.referencias.FormaPagamentoDtoRef;
import com.kaique.ifood.dto.referencias.RestauranteIdDtoRef;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDtoRequest {

	@Valid
	@NotNull
	private FormaPagamentoDtoRef formaPagamento;
	
	@Valid
	@NotNull
	private RestauranteIdDtoRef restaurante;
	
	@Valid
	@NotNull
	private EnderecoDtoRequest enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoDtoRequest> itens;

}
