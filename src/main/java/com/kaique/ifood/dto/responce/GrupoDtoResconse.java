package com.kaique.ifood.dto.responce;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoDtoResconse {

	private Long id;
	private String nome;
	private List<PermissaoDtoResponce> permissoes = new ArrayList<>();

}
