package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.CozinhaDtoRequest;
import com.kaique.ifood.entities.Cozinha;

@Component
public class CozinhaDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Cozinha converteDtopara(CozinhaDtoRequest dto) {
		return modelMapper.map(dto, Cozinha.class);
	}
	
	public void copiaPropiedades(CozinhaDtoRequest dto , Cozinha cozinha) {
		modelMapper.map(dto, cozinha);
	}
}
