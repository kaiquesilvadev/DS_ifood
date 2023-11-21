package com.kaique.ifood.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaique.ifood.entities.Permissao;

public class GrupoMixin {

	@JsonIgnore
	private List<Permissao> permissoes = new ArrayList<>();
}
