package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kaique.ifood.controlles.UsuarioController;
import com.kaique.ifood.dto.request.AtualizaUsuarioDtoRequest;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.links.LinkManager;

@Component
public class UsuarioDtoConversor extends RepresentationModelAssemblerSupport<Usuario , UsuarioDtoResponce> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LinkManager linkManager;

	public UsuarioDtoConversor() {
		super(UsuarioController.class, UsuarioDtoResponce.class);
	
	}

	public Usuario converteDto(UsuarioDtoRequest dto) {
		return modelMapper.map(dto, Usuario.class);
	}
	
	public UsuarioDtoResponce converteUsuario(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDtoResponce.class);
	}

	public void CopiaPropiedades(UsuarioDtoRequest dto, Usuario usuario) {
		modelMapper.map(dto, usuario);
	}
	
	public void CopiaPropiedadesAtualizacao(AtualizaUsuarioDtoRequest dto, Usuario usuario) {
		modelMapper.map(dto, usuario);
	}


	public List<UsuarioDtoResponce> ListaResponce(List<Usuario> lista) {
		return lista.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());

	}

	@Override
	public UsuarioDtoResponce toModel(Usuario entity) {
		var usuarioDTO = modelMapper.map(entity, UsuarioDtoResponce.class);
		usuarioDTO = linkManager.linkToUsuario(usuarioDTO);
		return usuarioDTO;
	}
}
