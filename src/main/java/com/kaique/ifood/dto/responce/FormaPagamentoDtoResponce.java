package com.kaique.ifood.dto.responce;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDtoResponce extends RepresentationModel<FormaPagamentoDtoResponce> {

	private Long id;
	private String descricao;
}
