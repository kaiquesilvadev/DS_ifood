package com.kaique.ifood.dto.responce;

import com.kaique.ifood.entities.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResponce {

	private Long id;
	private String nome;
	private Estado estado;
}
