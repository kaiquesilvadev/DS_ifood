package com.kaique.ifood.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.PedidoDtoConverso;
import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.entities.Produto;
import com.kaique.ifood.exception.CidadeNaoEncontradaException;
import com.kaique.ifood.exception.FormaPagamentoNaoAssociadoException;
import com.kaique.ifood.exception.FormaPagamentoNaoEncontradaException;
import com.kaique.ifood.exception.NegocioException;
import com.kaique.ifood.exception.PedidoNaoEncontradoException;
import com.kaique.ifood.exception.ProdutoNaoEncontradoException;
import com.kaique.ifood.exception.RestauranteNaoEncontradaException;
import com.kaique.ifood.repositories.PedidoRepository;
import com.kaique.ifood.repositories.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
public class EmissaoPedidoServices {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private PedidoDtoConverso converso;

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private FormaPagamentoService fPService;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UserSecurityService securityService;

	public Page<Pedido> lista(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Pedido buscaPorCodigo(String codigo) {
		Pedido pedido = repository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
		Hibernate.initialize(pedido.getItens());
		return pedido;
	}

	@Transactional
	public Pedido criarPedido(PedidoDtoRequest dtoRequest) {
		Pedido pedido = converso.converteDto(dtoRequest);
		pedido.setUsuarioCliente(securityService.authenticated());

		validaPedido(pedido);
		validaItens(pedido);
		pedido.definirTaxaFrete();
		pedido.calcularValorTotal();
		return repository.save(pedido);
	}

	private void validaPedido(Pedido pedido) {

		try {
			long idRestaurante = pedido.getRestaurante().getId();
			long idFormaPagamento = pedido.getFormaPagamento().getId();
			long idCidade = pedido.getEnderecoEntrega().getCidade().getId();
			restauranteService.buscaPorId(idRestaurante);
			fPService.buscaPorId(idFormaPagamento);
			cidadeService.buscaPorId(idCidade);
			restauranteRepository
					.validaRestauranteFP(pedido.getRestaurante().getId(), pedido.getFormaPagamento().getId())
					.orElseThrow(() -> new FormaPagamentoNaoAssociadoException(idRestaurante, idFormaPagamento));

		} catch (FormaPagamentoNaoEncontradaException | RestauranteNaoEncontradaException
				| CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	private void validaItens(Pedido pedido) {
		try {
			long idRestaurante = pedido.getRestaurante().getId();
			pedido.getItens().forEach(itens -> {
				Produto produto = produtoService.buscaIdEmRestaurante(idRestaurante, itens.getProduto().getId());
				itens.setPedido(pedido);
				itens.setProduto(produto);
				itens.setPrecoUnitario(produto.getPreco());
			});
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
