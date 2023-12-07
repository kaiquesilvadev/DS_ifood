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
	public void confirado(String codigo) {
		Pedido pedido = pedidoServices.buscaPorCodigo(codigo);
		pedido.statusConfirmado();
	}
	
	@Transactional
	public void entregue(String codigo) {
		Pedido pedido = pedidoServices.buscaPorCodigo(codigo);
		pedido.stausEntregue();
	}
	
	@Transactional
	public void cancelado(String codigo) {
		Pedido pedido = pedidoServices.buscaPorCodigo(codigo);
		pedido.stausCancelado();
	}
}