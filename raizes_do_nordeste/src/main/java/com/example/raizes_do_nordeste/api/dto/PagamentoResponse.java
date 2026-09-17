package com.example.raizes_do_nordeste.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.raizes_do_nordeste.domain.enums.StatusPagamento;

public class PagamentoResponse {
	
	private Long id;
	private Long pedidoId;
	private BigDecimal valor;
	private StatusPagamento status;
	private LocalDateTime dataHora;
	
	public PagamentoResponse() {
		
	}
	
	public  PagamentoResponse(Long id,
							  Long pedidoId,
							  BigDecimal valor,
							  StatusPagamento status,
							  LocalDateTime dataHora) {
		this.id = id;
		this.pedidoId = pedidoId;
		this.valor = valor;
		this.status = status;
		this.dataHora = dataHora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	

}
