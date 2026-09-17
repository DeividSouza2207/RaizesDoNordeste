package com.example.raizes_do_nordeste.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.raizes_do_nordeste.domain.enums.CanalPedido;
import com.example.raizes_do_nordeste.domain.enums.StatusPedido;

public class PedidoResponse {
	
	private Long id;
	private Long clienteId;
	private Long UnidadeId;
	private String unidadeNome;
	private CanalPedido canalPedido;
	private StatusPedido status;
	private BigDecimal valorTotal;
	private LocalDateTime dataHora;
	private List<ItemPedidoResponse> itens;
	
	public PedidoResponse() {
		
	}
	
	public PedidoResponse(Long id,
						  Long clienteId,
						  Long UnidadeId,
						  String unidadeNome,
						  CanalPedido canalPedido,
						  StatusPedido status,
						  BigDecimal valorTotal,
						  LocalDateTime dataHora,
						  List<ItemPedidoResponse> itens) {
		
		this.id = id;
		this.clienteId = clienteId;
		this.UnidadeId = UnidadeId;
		this.unidadeNome = unidadeNome;
		this.canalPedido = canalPedido;
		this.status = status;
		this.valorTotal = valorTotal;
		this.dataHora = dataHora;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getUnidadeId() {
		return UnidadeId;
	}

	public void setUnidadeId(Long unidadeId) {
		UnidadeId = unidadeId;
	}

	public String getUnidadeNome() {
		return unidadeNome;
	}

	public void setUnidadeNome(String unidadeNome) {
		this.unidadeNome = unidadeNome;
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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public List<ItemPedidoResponse> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoResponse> itens) {
		this.itens = itens;
	}

}
