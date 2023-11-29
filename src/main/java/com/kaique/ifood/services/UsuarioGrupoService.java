package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.repositories.UsuarioRepositorie;

import jakarta.transaction.Transactional;

@Service
public class UsuarioGrupoService {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private UsuarioService usuarioService;

	public List<Grupo> ListaGrupos(Long usuarioId) {
		usuarioService.buscarPorId(usuarioId);
		return repositorie.listaGruposDeUsuario(usuarioId);
	}

	@Transactional
	public Usuario adicionarGrupos(Long usuarioId , Long grupoId) {
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		Grupo grupo = grupoService.buscaPorId(grupoId);
		usuario.getGrupos().add(grupo);
		return usuario;
	}
	
	@Transactional
	public Usuario removerGrupos(Long usuarioId , Long grupoId) {
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		Grupo grupo = grupoService.buscaPorId(grupoId);
		usuario.getGrupos().remove(grupo);
		return usuario;
	}

}
