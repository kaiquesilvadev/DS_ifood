package com.kaique.ifood.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.exception.PedidoNaoEncontradoException;
import com.kaique.ifood.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoServices {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PedidoDtoConverso converso;
	
	public List<Pedido> lista() {
		return repository.findAll();
	}
	
	@Transactional
	public Pedido buscaPorid(Long id) {
		Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));
		Hibernate.initialize(pedido.getItens());
		return pedido;
	}
	
	@Transactional
	public Pedido adiciona(PedidoDtoRequest dtoRequest) {
		Pedido pedido = converso.converteDto(dtoRequest);
		//TODO : falta a logica da implementação
		
		return null;
		
		//return repository.save(pedido);
	}
}
