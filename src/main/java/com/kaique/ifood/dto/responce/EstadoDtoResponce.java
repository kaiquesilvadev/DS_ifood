package com.kaique.ifood.dto.responce;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDtoResponce extends RepresentationModel<EstadoDtoResponce> {

	private Long id;
	private String nome;
}
