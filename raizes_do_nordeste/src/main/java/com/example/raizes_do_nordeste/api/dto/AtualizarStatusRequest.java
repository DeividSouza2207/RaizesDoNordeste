package com.example.raizes_do_nordeste.api.dto;

import com.example.raizes_do_nordeste.domain.enums.StatusPedido;

public class AtualizarStatusRequest {
	
	private StatusPedido status;
	
	public AtualizarStatusRequest() {
	}
	
	public StatusPedido getStatus() {
		return status;
	}
	
	public void setStatus(StatusPedido status) {
		this.status = status;
	}

}
