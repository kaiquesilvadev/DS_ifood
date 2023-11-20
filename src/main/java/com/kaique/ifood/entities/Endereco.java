package com.kaique.ifood.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "endereco_cep")
	private String Cep;

	@Column(name = "endereco_logradouro")
	private String logradouro;

	@Column(name = "endereco_numero")
	private String numero;

	@Column(name = "endereco_complemento")
	private String complemento;

	@Column(name = "endereco_bairro")
	private String bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
}
