package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.entities.ItemPedido;

@Component
public class ItemPedidoDtoConverso {

	@Autowired
	private ModelMapper modelMapper;
	

	
	public ItemPedidoDtoResponce convertePedido(ItemPedido item) {
		return modelMapper.map( item , ItemPedidoDtoResponce.class);
	}
	
	public List<ItemPedidoDtoResponce> listaDto(List<ItemPedido> lista) {
		return lista.stream().map(Pedido -> convertePedido(Pedido)).collect(Collectors.toList());
	}
}
