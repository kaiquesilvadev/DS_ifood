package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.EnderecoDtoRequest;
import com.kaique.ifood.entities.Restaurante;

@Component
public class EnderecoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Restaurante converteApenasEndereco(EnderecoDtoRequest dto) {
		return modelMapper.map(dto, Restaurante.class);
	}
	
	public void CopiaPropiedades(EnderecoDtoRequest dto , Restaurante restaurante) {
		
		
		modelMapper.map(dto , restaurante);
	}}
