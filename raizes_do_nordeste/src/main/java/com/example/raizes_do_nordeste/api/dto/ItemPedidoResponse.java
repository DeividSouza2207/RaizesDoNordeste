package com.example.raizes_do_nordeste.api.dto;

import java.math.BigDecimal;

public class ItemPedidoResponse {
	
	private Long produtoId;
	private String produtoNome;
	private Integer quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal subtotal;
	
	public ItemPedidoResponse() {
		
	}
	
	public ItemPedidoResponse(Long produtoId, String produtoNome, Integer quantidade,
								BigDecimal valorUnitario, BigDecimal subtotal) {
		this.produtoId = produtoId;
		this.produtoNome = produtoNome;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.subtotal = subtotal;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
	

}
