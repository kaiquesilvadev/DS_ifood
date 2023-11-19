package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.FormaDePagamentoDtoRequest;
import com.kaique.ifood.entities.FormaPagamento;

@Component
public class FormaPagamentoDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();
	
	public FormaPagamento ConversorDto(FormaDePagamentoDtoRequest dto) {
		return modelMapper.map(dto, FormaPagamento.class);
	}
	
	public void CopiaPropiedades(FormaDePagamentoDtoRequest dto , FormaPagamento formaPagamento) {
		modelMapper.map(dto, formaPagamento);
	}
}
