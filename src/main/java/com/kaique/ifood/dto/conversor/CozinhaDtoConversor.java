package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.CozinhaDtoRequest;
import com.kaique.ifood.dto.responce.CozinhaDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.entities.Cozinha;
import com.kaique.ifood.entities.Pedido;

@Component
public class CozinhaDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public Cozinha converteDto(CozinhaDtoRequest dto) {
		return modelMapper.map(dto, Cozinha.class);
	}
	
	public CozinhaDtoResponce converteEntity(Cozinha entity) {
		return modelMapper.map(entity, CozinhaDtoResponce.class);
	}
	
	public void copiaPropiedades(CozinhaDtoRequest dto , Cozinha cozinha) {
		modelMapper.map(dto, cozinha);
	}
	
	public List<CozinhaDtoResponce> listaDto(List<Cozinha> lista) {
		return lista.stream().map(cozinha -> converteEntity(cozinha)).collect(Collectors.toList());
	}
}
