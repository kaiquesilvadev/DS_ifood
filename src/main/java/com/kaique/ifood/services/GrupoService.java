package com.kaique.ifood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.conversor.GrupoDtoConversor;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Permissao;
import com.kaique.ifood.exception.GrupoNaoEncontradoException;
import com.kaique.ifood.repositories.GrupoRepositorie;

import jakarta.transaction.Transactional;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepositorie repositorie;

	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private GrupoDtoConversor conversor;

	public List<Grupo> lista() {
		return repositorie.findAll();
	}

	public Grupo buscaPorId(Long id) {
		return repositorie.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	@Transactional
	public Grupo adiciona(GrupoDtoRequest dto) {
		Grupo grupo = conversor.converteDto(dto);
		return repositorie.save(grupo);
	}
	
	@Transactional
	public Grupo referencia(Long grupoId, Long permissaoId) {
		Grupo grupo = buscaPorId(grupoId);
		Permissao permissao = permissaoService.buscaPorId(permissaoId);
		grupo.getPermissoes().add(permissao);
		return grupo;
	}
	
	@Transactional
	public Grupo removerPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscaPorId(grupoId);
		Permissao permissao = permissaoService.buscaPorId(permissaoId);
		grupo.getPermissoes().remove(permissao);
		return grupo;
	}

	@Transactional
	public Grupo atualiza(GrupoDtoRequest dtoRequest, Long id) {

		Grupo grupo = this.buscaPorId(id);
		conversor.copiaPropiedades(dtoRequest, grupo);
		return repositorie.save(grupo);
	}

	@Transactional
	public void deleta(Long id) {
		this.buscaPorId(id);
		repositorie.deleteById(id);
	}

	
}
