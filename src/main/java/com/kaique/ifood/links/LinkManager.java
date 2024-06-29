package com.kaique.ifood.links;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.CidadeController;
import com.kaique.ifood.controlles.EstadoController;
import com.kaique.ifood.controlles.FormaPagamentoController;
import com.kaique.ifood.controlles.PedidoControlle;
import com.kaique.ifood.controlles.RestauranteController;
import com.kaique.ifood.controlles.RestauranteProdutoController;
import com.kaique.ifood.controlles.UsuarioController;
import com.kaique.ifood.controlles.UsuarioGrupoController;
import com.kaique.ifood.dto.responce.EstadoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.dto.responce.RestauranteDtoResponce;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.entities.Restaurante;

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
	        var linkCidade = linkTo(methodOn(CidadeController.class).buscarPorId(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel();

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
	            itemPedidoDTO.add(linkTo(methodOn(RestauranteProdutoController.class).buscaIdDeProdutoEmRestaurante(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProdutoId())).withSelfRel());
	        });

	        return pedidoDTO;
	    }
	  
	  public EstadoDtoResponce linkToEstado(EstadoDtoResponce estadoDTO) {

	        Link linkBuscarPorId = linkTo(methodOn(EstadoController.class).buscaPorId(estadoDTO.getId())).withSelfRel();
	        Link linkListar = linkTo(methodOn(EstadoController.class).listar()).withRel("lista");

	        estadoDTO.add(linkBuscarPorId);
	        estadoDTO.add(linkListar);

	        return estadoDTO;

	    }
	  
	   public RestauranteDtoResponce linkToRestaurante(RestauranteDtoResponce restauranteDTO, Restaurante restaurante) {
	        var linkBuscarPorId = linkTo(methodOn(RestauranteController.class).buscaPorId(restaurante.getId())).withSelfRel();
	        var linkListar = linkTo(methodOn(RestauranteController.class).listar(null)).withRel("listar");

	        restauranteDTO.add(linkBuscarPorId);
	        restauranteDTO.add(linkListar);

	        return restauranteDTO;
	    }
	   
	   public UsuarioDtoResponce linkToUsuario(UsuarioDtoResponce usuarioDTO) {
	        Link linkBuscarPorId = linkTo(methodOn(UsuarioController.class).buscaPorId(usuarioDTO.getId())).withSelfRel();
	        Link linkListar = linkTo(methodOn(UsuarioController.class).list()).withRel("lista");
	        Link linkGrupos = linkTo(methodOn(UsuarioGrupoController.class).ListaGrupo(usuarioDTO.getId())).withSelfRel();

	        usuarioDTO.add(linkBuscarPorId);
	        usuarioDTO.add(linkListar);
	        usuarioDTO.add(linkGrupos);

	        return usuarioDTO;
	    }
	}