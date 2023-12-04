package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.entities.ItemPedido;
import com.kaique.ifood.entities.Pedido;

@Component
public class PedidoDtoConverso {

	@Autowired
	private ModelMapper modelMapper;
	
	public void mapea() {
		modelMapper.createTypeMap(ItemPedidoDtoResponce.class, ItemPedido.class)
		.addMappings(mapper -> mapper.skip(ItemPedido::setId));
	}
	
	public PedidoDtoResponce convertePedido(Pedido pedido) {
		return modelMapper.map(pedido , PedidoDtoResponce.class);
	}
	
	public Pedido converteDto(PedidoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest , Pedido.class);
	}
	
	public List<PedidoDtoResponce> listaDto(List<Pedido> lista) {
		return lista.stream().map(Pedido -> convertePedido(Pedido)).collect(Collectors.toList());
	}
}
