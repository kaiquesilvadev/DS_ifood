package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.FormaPagamentoDtoRequest;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.FormaPagamentoDtoResponce;
import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.entities.Grupo;

@Component
public class FormaPagamentoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public FormaPagamento ConversorDto(FormaPagamentoDtoRequest dto) {
		return modelMapper.map(dto, FormaPagamento.class);
	}
	
	public FormaPagamentoDtoResponce ConversorEntity(FormaPagamento entity) {
		return modelMapper.map(entity, FormaPagamentoDtoResponce.class);
	}
	
	public void CopiaPropiedades(FormaPagamentoDtoRequest dto , FormaPagamento formaPagamento) {
		modelMapper.map(dto, formaPagamento);
	}
	
	public List<FormaPagamentoDtoResponce> listaDto(List<FormaPagamento> list) {
		return list.stream().map(formaPagamento -> ConversorEntity(formaPagamento)).collect(Collectors.toList());
	}
}
