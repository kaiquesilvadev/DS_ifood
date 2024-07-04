package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.PedidoOpenAPI;
import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.conversor.PedidoResumoDtoConverso;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.services.EmissaoPedidoServices;
import com.kaique.ifood.services.FluxoPedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/pedidos")
public class PedidoControlle implements PedidoOpenAPI{

	@Autowired
	private EmissaoPedidoServices services;
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;

	@Autowired
	private PedidoResumoDtoConverso conversoResumo;

	@Autowired
	private PedidoDtoConverso converso;

	@Override
	@GetMapping
	public Page<PedidoResumoDtoResponce> lista(Pageable pageable) {
		Page<Pedido> page = services.lista(pageable);
		List<PedidoResumoDtoResponce> DtoResponce = conversoResumo.listaDto(page.getContent());
		return new PageImpl<PedidoResumoDtoResponce>(DtoResponce, pageable, page.getTotalElements());
	}

	@Override
	@GetMapping("/{codigo}")
	public PedidoDtoResponce buscaPorCodigo(@PathVariable String codigo) {
		return converso.convertePedido(services.buscaPorCodigo(codigo));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoDtoResponce criarPedido(@Valid @RequestBody PedidoDtoRequest dtoRequest) {
		return converso.convertePedido(services.criarPedido(dtoRequest));
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{codigo}/confirmacao")
	public void confirmaPedido(@PathVariable String codigo) {
		fluxoPedidoService.confirmado(codigo);
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{codigo}/entregue")
	public void pedidoEntregue(@PathVariable String codigo) {
		fluxoPedidoService.entregue(codigo);
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{codigo}/cancelamento")
	public void pedidoCancelado(@PathVariable String codigo) {
		fluxoPedidoService.cancelado(codigo);
	}
}
