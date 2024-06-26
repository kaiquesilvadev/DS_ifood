package com.kaique.ifood.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.kaique.ifood.enuns.StatusPedido;
import com.kaique.ifood.exception.ViolacaoStatusPedidoException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.CRIADO;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;

	@Embedded
	private Endereco enderecoEntrega;

	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioCliente;

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

	/*
	 * TODO : analisar melhor depois
	 * 
	 * Quando você tem uma associação entre duas entidades e aplica CascadeType.ALL
	 * a essa associação, significa que as operações de persistência, remoção,
	 * atualização e recuperação de uma entidade serão automaticamente propagadas
	 * para a entidade associada.
	 */
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {

		this.subTotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println("passou no teste");
		this.valorTotal = this.subTotal.add(this.taxaFrete);

	}

	public void definirTaxaFrete() {
		BigDecimal taxa = getRestaurante().getTaxaFrete();

		if (taxa != null)
			setTaxaFrete(taxa);
		else
			setTaxaFrete(BigDecimal.ZERO);

	}

	public void statusConfirmado() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}

	public void stausEntregue() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}

	public void stausCancelado() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}

	/*
	 * É private para que apenas seja chamado dentro da classe, como a entidade é
	 * rica, as tratativas ficam nela mesma.
	 */
	private void setStatus(StatusPedido novoStatus) {
		if (!getStatusPedido().podeAlterarPara(novoStatus)) {
			throw new ViolacaoStatusPedidoException(getStatusPedido(), novoStatus);
		}

		this.statusPedido = novoStatus;
	}

	/*
	 * @PrePersist: Essa anotação é usada em JPA (Java Persistence API) para indicar
	 * que um método deve ser executado antes de uma entidade ser persistida (ou
	 * seja, antes de ser inserida no banco de dados).
	 */

	@PrePersist
	private void geraCodigo() {
		setCodigo(UUID.randomUUID().toString()); // gera um código unico no banco de dados 
	}
	

	public Boolean podeSerConfirmado() {
		return getStatusPedido().podeAlterarPara(StatusPedido.CONFIRMADO);
	}

	public Boolean podeSerEntregue() {
		return getStatusPedido().podeAlterarPara(StatusPedido.ENTREGUE);
	}

	public Boolean podeSerCancelado() {
		return getStatusPedido().podeAlterarPara(StatusPedido.CANCELADO);
	}

}
