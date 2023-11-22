package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.entities.Usuario;

@Component
public class UsuarioDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Usuario converteDto(UsuarioDtoRequest dto) {
		return modelMapper.map(dto, Usuario.class);
	}

	public void CopiaPropiedades(UsuarioDtoRequest dto, Usuario usuario) {
		modelMapper.map(dto, usuario);
	}
}
