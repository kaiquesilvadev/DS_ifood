package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.entities.ItemPedido;
import com.kaique.ifood.entities.Pedido;

@Component
public class PedidoResumoDtoConverso {

	@Autowired
	private ModelMapper modelMapper;

	public void test() {
		modelMapper.createTypeMap(ItemPedidoDtoResponce.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));
	}

	public PedidoResumoDtoResponce convertePedido(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoDtoResponce.class);
	}

	public List<PedidoResumoDtoResponce> listaDto(List<Pedido> lista) {
		return lista.stream().map(Pedido -> convertePedido(Pedido)).collect(Collectors.toList());
	}
}
