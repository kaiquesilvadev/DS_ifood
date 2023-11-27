package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.PermissaoDtoRequest;
import com.kaique.ifood.entities.Permissao;

@Component
public class PermissaodtoConversor {

	@Autowired
	private ModelMapper modelMapper;

	public Permissao converDto(PermissaoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest, Permissao.class);
	}
	
	public void copiaPropiedades(PermissaoDtoRequest dtoRequest , Permissao permissao) {
		modelMapper.map(dtoRequest, permissao);
	}
}
