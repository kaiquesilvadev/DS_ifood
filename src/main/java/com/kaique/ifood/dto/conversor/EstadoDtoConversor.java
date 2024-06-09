package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.EstadoDtoResponce;
import com.kaique.ifood.entities.Estado;
import com.kaique.ifood.entities.Grupo;

@Component
public class EstadoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Estado conversorDto(EstadoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest, Estado.class);
	}
	
	public EstadoDtoResponce conversorEntety(Estado entity) {
		return modelMapper.map(entity, EstadoDtoResponce.class);
	}
	
	public void copiaPropiedades(EstadoDtoRequest dto , Estado estado) {
		modelMapper.map(estado, estado);
	}
	
	public List<EstadoDtoResponce> listaDto(List<Estado> list) {
		return list.stream().map(estado -> conversorEntety(estado)).collect(Collectors.toList());
	}
}
