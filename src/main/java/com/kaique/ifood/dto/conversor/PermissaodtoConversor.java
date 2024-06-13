package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.request.PermissaoDtoRequest;
import com.kaique.ifood.dto.responce.PermissaoDtoResponce;
import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Permissao;

@Component
public class PermissaodtoConversor {

	@Autowired
	private ModelMapper modelMapper;

	public Permissao converteDto(PermissaoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest, Permissao.class);
	}
	
	public PermissaoDtoResponce converteEntity(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDtoResponce.class);
	}
	
	public void copiaPropiedades(PermissaoDtoRequest dtoRequest , Permissao permissao) {
		modelMapper.map(dtoRequest, permissao);
	}
	
	public List<PermissaoDtoResponce> listaDto(List<Permissao> list) {
		return list.stream().map(permissao -> modelMapper.map(permissao , PermissaoDtoResponce.class)).collect(Collectors.toList());
	}
}
