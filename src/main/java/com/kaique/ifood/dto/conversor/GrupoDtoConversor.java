package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.entities.Grupo;

@Component
public class GrupoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	public Grupo converteDto(GrupoDtoRequest dto) {
		return modelMapper.map(dto, Grupo.class);
	}

	public void copiaPropiedades(GrupoDtoRequest dto, Grupo grupo) {
		modelMapper.map(dto, grupo);
	}
}
