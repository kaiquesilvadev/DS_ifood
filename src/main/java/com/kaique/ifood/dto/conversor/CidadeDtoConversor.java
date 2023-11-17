package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.CidadeDtoRequest;
import com.kaique.ifood.entities.Cidade;
import com.kaique.ifood.entities.Estado;

@Component
public class CidadeDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	public Cidade converteDto(CidadeDtoRequest dto) {
		return modelMapper.map(dto, Cidade.class);
	}

	public void copiaPropiedades(CidadeDtoRequest dto , Cidade cidade) {
		cidade.setEstado(new Estado());
		
		modelMapper.map(dto , cidade);
	}
}
