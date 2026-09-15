package com.example.raizes_do_nordeste.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.application.service.PagamentoService;
import com.example.raizes_do_nordeste.domain.entity.Pagamento;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService) {
		
		this.pagamentoService = pagamentoService;
	}
	
	@PostMapping("/{pedidoId}")
	public ResponseEntity<Pagamento> efetuarPagamento(@PathVariable Long pedidoId) {
		
		Pagamento pagamento = pagamentoService.efetuarPagamento(pedidoId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
		
	}

}
