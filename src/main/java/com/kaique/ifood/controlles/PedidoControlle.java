package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.conversor.PedidoResumoDtoConverso;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.services.EmissaoPedidoServices;
import com.kaique.ifood.services.FluxoPedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlle {

	@Autowired
	private EmissaoPedidoServices services;
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;

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
		return converso.convertePedido(services.buscaPorId(id));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoDtoResponce lista(@Valid @RequestBody PedidoDtoRequest dtoRequest) {
		return converso.convertePedido(services.criarPedido(dtoRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{pedidoId}/confirmacao")
	public void confirmaPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirado(pedidoId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{pedidoId}/entregue")
	public void pedidoEntregue(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregue(pedidoId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{pedidoId}/cancelamento")
	public void pedidoCancelado(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelado(pedidoId);
	}
}
