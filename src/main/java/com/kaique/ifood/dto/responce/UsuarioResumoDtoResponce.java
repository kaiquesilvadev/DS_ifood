package com.kaique.ifood.dto.responce;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResumoDtoResponce extends  RepresentationModel<UsuarioResumoDtoResponce> {

	private Long id;
	private String nome;
	private String email;
}
