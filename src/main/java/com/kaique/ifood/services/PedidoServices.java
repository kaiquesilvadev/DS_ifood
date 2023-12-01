package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.exception.PedidoNaoEncontradoException;
import com.kaique.ifood.repositories.PedidoRepository;

@Service
public class PedidoServices {

	@Autowired
	private PedidoRepository repository;
	
	public List<Pedido> lista() {
		return repository.findAll();
	}
	
	public Pedido buscaPorid(Long id) {
		return repository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
}
