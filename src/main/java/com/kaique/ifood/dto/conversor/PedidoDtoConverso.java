package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.PedidoControlle;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.entities.ItemPedido;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.links.LinkManager;

@Component
public class PedidoDtoConverso extends RepresentationModelAssemblerSupport<Pedido, PedidoDtoResponce> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LinkManager linkManager;
	
	public PedidoDtoConverso() {
		super(PedidoControlle.class, PedidoDtoResponce.class);
	}
	
	public void mapea() {
		modelMapper.createTypeMap(ItemPedidoDtoResponce.class, ItemPedido.class)
		.addMappings(mapper -> mapper.skip(ItemPedido::setId));
	}
	
	public PedidoDtoResponce convertePedido(Pedido pedido) {
		return toModel(pedido);
	}
	
	public Pedido converteDto(PedidoDtoRequest dtoRequest) {
		return modelMapper.map(dtoRequest , Pedido.class);
	}
	
	public List<PedidoDtoResponce> listaDto(List<Pedido> lista) {
		return lista.stream().map(Pedido -> toModel(Pedido)).collect(Collectors.toList());
	}

	@Override
	public PedidoDtoResponce toModel(Pedido entity) {
		var pedidoDTO = modelMapper.map(entity, PedidoDtoResponce.class);
		pedidoDTO = linkManager.linkToPedido(pedidoDTO, entity);
		return pedidoDTO;
	}
}
