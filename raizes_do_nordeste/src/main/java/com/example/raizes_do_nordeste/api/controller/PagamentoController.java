package com.example.raizes_do_nordeste.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.api.dto.PagamentoResponse;
import com.example.raizes_do_nordeste.application.service.PagamentoService;
import com.example.raizes_do_nordeste.domain.entity.Pagamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/pagamentos")
@SecurityRequirement(name = "bearerAuth")
public class PagamentoController {
	
	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService) {
		
		this.pagamentoService = pagamentoService;
	}
	
	@Operation(
			summary = "Realiza o pagamento de um pedido",
			description = "Executa um pagamento MOCK de um pedido que esteja aguardando pagamento.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Pagamento realizado com sucesso."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
		@ApiResponse(responseCode = "404", description ="Pedido não encontrado.")
		
	})
	
	@PostMapping("/{pedidoId}")
	public ResponseEntity<PagamentoResponse> efetuarPagamento(@PathVariable Long pedidoId,
																@RequestParam String resultado) {
		
		Pagamento pagamento = pagamentoService.efetuarPagamento(pedidoId, resultado);
		
		PagamentoResponse response = new PagamentoResponse(
				pagamento.getId(),
				pagamento.getPedido().getId(),
				pagamento.getValor(),
				pagamento.getStatus(),
				pagamento.getDataHora());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}

}
