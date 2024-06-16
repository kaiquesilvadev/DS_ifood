package com.kaique.ifood.dto.responce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.kaique.ifood.dto.referencias.GrupoDtoRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoResponce extends RepresentationModel<UsuarioDtoResponce> {


	private Long id;
	private String nome;
	private String email;
	private List<GrupoDtoRef> grupos = new ArrayList<>();
}
