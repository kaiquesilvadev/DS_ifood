package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.GrupoDtoResconse;
import com.kaique.ifood.entities.Grupo;

@Component
public class GrupoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	public Grupo converteDto(GrupoDtoRequest dto) {
		return modelMapper.map(dto, Grupo.class);
	}

	public GrupoDtoResconse  converteGrupo(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDtoResconse.class);
	}

	public void copiaPropiedades(GrupoDtoRequest dto, Grupo grupo) {
		modelMapper.map(dto, grupo);
	}
	
	public List<GrupoDtoResconse> lista(List<Grupo> list) {
		return list.stream().map(grupo -> modelMapper.map(grupo, GrupoDtoResconse.class)).collect(Collectors.toList());
	}
}
