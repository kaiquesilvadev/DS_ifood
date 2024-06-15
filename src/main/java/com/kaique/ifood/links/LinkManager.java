package com.kaique.ifood.links;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.CidadeController;
import com.kaique.ifood.controlles.FormaPagamentoController;
import com.kaique.ifood.controlles.PedidoControlle;
import com.kaique.ifood.controlles.RestauranteController;
import com.kaique.ifood.controlles.RestauranteProdutoController;
import com.kaique.ifood.controlles.UsuarioController;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.entities.Pedido;

import lombok.var;

@Component
public class LinkManager {
	
	  public PedidoResumoDtoResponce linkToPedidoResumo(PedidoResumoDtoResponce pedidoResumoDto, Pedido pedido) {

	        // Pedido
	        var linkBuscarPorId = linkTo(methodOn(PedidoControlle.class).buscaPorCodigo(pedido.getCodigo())).withSelfRel();

	        var variables = new TemplateVariables(
	                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
	                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
	                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
	        );

	        var filterVariables = new TemplateVariables(
	                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
	                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
	                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
	        );

	        String pedidosUrl = linkTo(PedidoControlle.class).toUri().toString();

	        UriTemplate uriTemplate = UriTemplate.of(pedidosUrl, variables.concat(filterVariables));
	        Link linkListaResumida = Link.of(uriTemplate.toString()).withRel("lista-resumida");

	        // Usuario
	        var linkUsuario = linkTo(methodOn(UsuarioController.class).buscaPorId(pedido.getUsuarioCliente().getId())).withSelfRel();

	        // Restaurante
	        var linkRestaurante = linkTo(methodOn(RestauranteController.class).buscaPorId(pedido.getRestaurante().getId())).withSelfRel();

	        pedidoResumoDto.add(linkBuscarPorId);
	        pedidoResumoDto.add(linkListaResumida);
	        pedidoResumoDto.getCliente().add(linkUsuario);
	        pedidoResumoDto.getRestaurante().add(linkRestaurante);

	        return pedidoResumoDto;

	    }
	  
	  public PedidoDtoResponce linkToPedido(PedidoDtoResponce pedidoDTO, Pedido pedido) {
	        // Pedido
	        var linkBuscarPorId = linkTo(methodOn(PedidoControlle.class).buscaPorCodigo(pedido.getCodigo())).withSelfRel();

	        // Usuario
	        var linkUsuario = linkTo(methodOn(UsuarioController.class).buscaPorId(pedido.getUsuarioCliente().getId())).withSelfRel();

	        // Cidade
	        var linkCidade = linkTo(methodOn(CidadeController.class).buscaPorId(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel();

	        // Restaurante
	        var linkRestaurante = linkTo(methodOn(RestauranteController.class).buscaPorId(pedido.getRestaurante().getId())).withSelfRel();

	        // Forma Pagamento
	        var linkFormaPagamento = linkTo(methodOn(FormaPagamentoController.class).buscaPorId(pedido.getFormaPagamento().getId())).withSelfRel();

	        pedidoDTO.add(linkBuscarPorId);
	        pedidoDTO.getUsuarioCliente().add(linkUsuario);
	        pedidoDTO.getEnderecoEntrega().add(linkCidade);
	        pedidoDTO.getRestaurante().add(linkRestaurante);
	        pedidoDTO.getFormaPagamento().add(linkFormaPagamento);

	        pedidoDTO.getItens().forEach(itemPedidoDTO -> {
	            itemPedidoDTO.add(linkTo(methodOn(RestauranteProdutoController.class).buscaidEmRestaurante(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProdutoId())).withSelfRel());
	        });

	        return pedidoDTO;
	    }
	}