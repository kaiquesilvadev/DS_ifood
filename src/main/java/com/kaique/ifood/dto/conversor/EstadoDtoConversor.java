package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.entities.Estado;

@Component
public class EstadoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Estado conversorDto(EstadoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest, Estado.class);
	}
	
	public void copiaPropiedades(EstadoDtoRequest dto , Estado estado) {
		modelMapper.map(estado, estado);
	}
}
