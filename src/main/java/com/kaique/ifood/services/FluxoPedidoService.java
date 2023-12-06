package com.kaique.ifood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.enuns.StatusPedido;
import com.kaique.ifood.exception.ViolacaoStatusPedidoException;

import jakarta.transaction.Transactional;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoServices pedidoServices;
	
	@Transactional
	public void confirado(Long pedidoId) {
		Pedido pedido = pedidoServices.buscaPorId(pedidoId);
		
		if(!pedido.getStatusPedido().equals(StatusPedido.CRIADO)) {
			throw new ViolacaoStatusPedidoException(pedido.getStatusPedido() , StatusPedido.CONFIRMADO , pedido.getId());
		}
		
		pedido.confirmacao(pedido);
	}
	
	@Transactional
	public void entregue(Long pedidoId) {
		Pedido pedido = pedidoServices.buscaPorId(pedidoId);
		
		if(!pedido.getStatusPedido().equals(StatusPedido.CONFIRMADO)) {
			throw new ViolacaoStatusPedidoException(pedido.getStatusPedido() , StatusPedido.ENTREGUE , pedido.getId());
		}
		
		pedido.entregue(pedido);
	}
}
