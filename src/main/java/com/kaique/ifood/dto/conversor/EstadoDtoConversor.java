package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.EstadoController;
import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.dto.responce.EstadoDtoResponce;
import com.kaique.ifood.entities.Estado;
import com.kaique.ifood.links.LinkManager;

@Component
public class EstadoDtoConversor extends RepresentationModelAssemblerSupport<Estado, EstadoDtoResponce> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LinkManager linkManager;

	public EstadoDtoConversor() {
		super(EstadoController.class, EstadoDtoResponce.class);
		
	}

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
		return list.stream().map(estado -> toModel(estado)).collect(Collectors.toList());
	}

	@Override
	public EstadoDtoResponce toModel(Estado entity) {
		var estadoDTO =  modelMapper.map(entity, EstadoDtoResponce.class);
		estadoDTO = linkManager.linkToEstado(estadoDTO);
		return estadoDTO;
	}
}
