package com.example.raizes_do_nordeste.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.raizes_do_nordeste.domain.enums.CanalPedido;
import com.example.raizes_do_nordeste.domain.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cliente_id", nullable = false)
	private Usuario cliente;

	@ManyToOne(optional = false)
	@JoinColumn(name="unidade_id", nullable = false)
	private Unidade unidade;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CanalPedido canalPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPedido status = StatusPedido.AGUARDANDO_PAGAMENTO;
	
	@Column(nullable = false, precision=10, scale=2)
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@Column(nullable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@OneToMany(mappedBy ="pedido",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();	
	
	
	public Pedido() {
	   
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public CanalPedido getCanalPedido() {
		return canalPedido;
	}

	public void setCanalPedido(CanalPedido canalPedido) {
		this.canalPedido = canalPedido;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}


	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
}
