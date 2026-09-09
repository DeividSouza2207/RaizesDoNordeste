package com.example.raizes_do_nordeste.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="itens_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	@Column(name="quantidade", nullable = false)
	private Integer quantidade;

	@Column(name="valor", nullable = false, precision=10, scale=2)
	private BigDecimal valorUnitario;
	
	@Column(name="subTotal", nullable = false, precision=10, scale=2)
	private BigDecimal subTotal;
	
	public ItemPedido() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitário() {
		return valorUnitario;
	}

	public void setValorUnitário(BigDecimal valorUnitário) {
		this.valorUnitario = valorUnitário;
	}

	public BigDecimal getSubtotal() {
		return subTotal;
	}

	public void setSubtotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
