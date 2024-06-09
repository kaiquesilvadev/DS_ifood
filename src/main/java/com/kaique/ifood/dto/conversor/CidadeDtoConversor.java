package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.CidadeDtoRequest;
import com.kaique.ifood.dto.responce.CidadeResponce;
import com.kaique.ifood.dto.responce.RestauranteResumoDtoResponce;
import com.kaique.ifood.entities.Cidade;
import com.kaique.ifood.entities.Estado;
import com.kaique.ifood.entities.Restaurante;

@Component
public class CidadeDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	public Cidade converteDto(CidadeDtoRequest dto) {
		return modelMapper.map(dto, Cidade.class);
	}
	
	public CidadeResponce converteEntity(Cidade cidade) {
		return modelMapper.map(cidade, CidadeResponce.class);
	}

	public void copiaPropiedades(CidadeDtoRequest dto , Cidade cidade) {
		cidade.setEstado(new Estado());
		
		modelMapper.map(dto , cidade);
	}
	
	public List<CidadeResponce> ConverteListEntity(List<Cidade> lista) {
		return lista.stream().map(cidade -> converteEntity(cidade)).collect(Collectors.toList());
	}
}
