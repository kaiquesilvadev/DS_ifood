package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.services.PedidoServices;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlle {

	@Autowired
	private PedidoServices services;
	
	@Autowired
	private PedidoDtoConverso converso;
	
	@GetMapping
	public List<PedidoDtoResponce> lista() {
		return converso.listaDto(services.lista());
	}
	
	@GetMapping("/{id}")
	public PedidoDtoResponce lista(@PathVariable Long id) {
		return converso.convertePedido(services.buscaPorid(id));
	}
}
