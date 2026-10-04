package com.example.raizes_do_nordeste.api.dto;

import java.util.List;

import com.example.raizes_do_nordeste.domain.enums.CanalPedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CriarPedidoRequest {
	
	@NotNull
	private Long unidadeId;
	
	@NotNull
	private CanalPedido canalPedido;
	
	@NotEmpty
	private List<@Valid ItemPedidoRequest> itens;
	
	public CriarPedidoRequest() {
		
	}

	public Long getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}

	public CanalPedido getCanalPedido() {
		return canalPedido;
	}

	public void setCanalPedido(CanalPedido canalPedido) {
		this.canalPedido = canalPedido;
	}

	public List<ItemPedidoRequest> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoRequest> itens) {
		this.itens = itens;
	}
	
	

}
