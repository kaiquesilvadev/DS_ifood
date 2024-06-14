package com.kaique.ifood.dto.responce;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDtoResponce {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataAtualizacao;
	private Boolean ativo = Boolean.TRUE;
	private EnderecoDtoResponce endereco;
	private CozinhaDtoResponce cozinha;
	private List<ProdutoDtoResponce> produtos = new ArrayList<>();;
	private List<FormaPagamentoDtoResponce> formaPagamentos = new ArrayList<>();
}
