package com.kaique.ifood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Pedido;

import jakarta.transaction.Transactional;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoServices pedidoServices;
	
	@Transactional
	public void confirado(Long pedidoId) {
		Pedido pedido = pedidoServices.buscaPorId(pedidoId);
		pedido.statusConfirmado();
	}
	
	@Transactional
	public void entregue(Long pedidoId) {
		Pedido pedido = pedidoServices.buscaPorId(pedidoId);
		pedido.stausEntregue();
	}
	
	@Transactional
	public void cancelado(Long pedidoId) {
		Pedido pedido = pedidoServices.buscaPorId(pedidoId);
		pedido.stausCancelado();
	}
}