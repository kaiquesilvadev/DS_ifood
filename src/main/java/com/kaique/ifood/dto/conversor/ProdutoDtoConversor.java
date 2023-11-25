package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.ProdutoDtoRequest;
import com.kaique.ifood.dto.responce.ProdutoDtoResponce;
import com.kaique.ifood.entities.Produto;

@Component
public class ProdutoDtoConversor {

	@Autowired
	private ModelMapper modelMapper;

	public Produto converteDto(ProdutoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest, Produto.class);
	}

	public ProdutoDtoResponce converteProduto(Produto produto) {
		return modelMapper.map(produto, ProdutoDtoResponce.class);
	}

	public List<ProdutoDtoResponce> ListDtoProduto(List<Produto> lista) {
		return lista.stream().map(produto -> converteProduto(produto)).collect(Collectors.toList());
	}
	
	public void copiaPropiedades(ProdutoDtoRequest dtoRequest , Produto produto) {	
		modelMapper.map(dtoRequest , produto);
	}
}
