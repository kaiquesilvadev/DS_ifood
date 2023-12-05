package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.conversor.PedidoResumoDtoConverso;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.services.PedidoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlle {

	@Autowired
	private PedidoServices services;

	@Autowired
	private PedidoResumoDtoConverso conversoResumo;

	@Autowired
	private PedidoDtoConverso converso;

	@GetMapping
	public List<PedidoResumoDtoResponce> lista() {
		return conversoResumo.listaDto(services.lista());
	}

	@GetMapping("/{id}")
	public PedidoDtoResponce lista(@PathVariable Long id) {
		return converso.convertePedido(services.buscaPorid(id));
	}

	@PostMapping
	public PedidoDtoResponce lista(@Valid @RequestBody PedidoDtoRequest dtoRequest) {
		return converso.convertePedido(services.adiciona(dtoRequest));
	}
}
