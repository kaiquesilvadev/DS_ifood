package com.kaique.ifood.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "tb_restaurante")
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@JoinColumn(name = "taxa_frete")
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	
	@Embedded
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;
	
	@OneToMany(mappedBy = "restaurante" , fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_Restaurante_forma_pagamento", 
	joinColumns = @JoinColumn(name = "restaurante_id"), 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamentos = new ArrayList<>();
	
	
}
