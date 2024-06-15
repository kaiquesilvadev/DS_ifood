package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.PedidoControlle;
import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.entities.ItemPedido;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.links.LinkManager;

@Component
public class PedidoResumoDtoConverso  extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDtoResponce> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LinkManager linkManager;
	
	public PedidoResumoDtoConverso() {
		super(PedidoControlle.class, PedidoResumoDtoResponce.class);
	}

	public void test() {
		modelMapper.createTypeMap(ItemPedidoDtoResponce.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));
	}

	public PedidoResumoDtoResponce convertePedido(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoDtoResponce.class);
	}

	public List<PedidoResumoDtoResponce> listaDto(List<Pedido> lista) {
		return lista.stream().map(Pedido -> toModel(Pedido)).collect(Collectors.toList());
	}

	@Override
	public PedidoResumoDtoResponce toModel(Pedido entity) {
		var pedidoDTO = modelMapper.map(entity, PedidoResumoDtoResponce.class);
		pedidoDTO = linkManager.linkToPedidoResumo(pedidoDTO, entity);
		return pedidoDTO;
	}
}
