package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.AtualizaUsuarioDtoRequest;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.entities.Usuario;

@Component
public class UsuarioDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

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
		return lista.stream().map(usuario -> converteUsuario(usuario)).collect(Collectors.toList());

	}
}
