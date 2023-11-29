package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.referencias.GrupoDtoRef;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.entities.Grupo;

@Component
public class GrupoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	public Grupo converteDto(GrupoDtoRequest dto) {
		return modelMapper.map(dto, Grupo.class);
	}

	public GrupoDtoRequest converteGrupo(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDtoRequest.class);
	}

	public void copiaPropiedades(GrupoDtoRequest dto, Grupo grupo) {
		modelMapper.map(dto, grupo);
	}

	public List<GrupoDtoRequest> listaDto(List<Grupo> list) {
		return list.stream().map(grupo -> modelMapper.map(grupo, GrupoDtoRequest.class)).collect(Collectors.toList());
	}
}
