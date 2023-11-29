package com.kaique.ifood.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaique.ifood.entities.Grupo;

public class UsuarioMixin {

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private List<Grupo> grupos = new ArrayList<>();
}
