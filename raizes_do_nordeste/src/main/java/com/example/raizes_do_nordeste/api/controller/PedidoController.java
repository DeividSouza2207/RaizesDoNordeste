package com.example.raizes_do_nordeste.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.api.dto.AtualizarStatusRequest;
import com.example.raizes_do_nordeste.api.dto.CriarPedidoRequest;
import com.example.raizes_do_nordeste.api.dto.PedidoResponse;
import com.example.raizes_do_nordeste.application.service.PedidoService;
import com.example.raizes_do_nordeste.domain.entity.Pedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearerAuth")
public class PedidoController {
	
	private final PedidoService pedidoService;
	
	public  PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@Operation(
			summary = "Cria um novo pedido",
			description = "Além de criar o pedido, valida os produtos e o estoque e também calcula o valor total.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Pedido criado com sucesso."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	})
	
	@PostMapping
	public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody CriarPedidoRequest request){
	
		Pedido novoPedido = pedidoService.criarPedido(request);
		
		PedidoResponse response = pedidoService.converterParaResponse(novoPedido);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
		
	}
	
	@Operation(
			summary = "Lista os pedidos",
			description = "Mostra os pedidos cadastrados.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Pedidos encontrados."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	})
	
	@GetMapping
	public ResponseEntity<List<PedidoResponse>> listarTodos() {
		
		List<PedidoResponse> response = pedidoService.listarTodos()
				.stream()
				.map(pedidoService::converterParaResponse)
				.toList();
		
		return ResponseEntity.ok(response);
		
	}
	
	@Operation(
			summary = "Encontra o pedido pelo id",
			description = "Mostra os dados de um pedido específico.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Pedido encontrado."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "404", description ="Pedido não encontrado."),
	})
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
		
		Pedido pedido = pedidoService.buscarPorId(id);
		
		return ResponseEntity.ok(pedidoService.converterParaResponse(pedido));
	}
	
	@Operation(
			summary = "Atualiza o status do pedido.",
			description = "Atualiza o status do pedido conforme as exigências.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Status atualizado com sucesso."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
		@ApiResponse(responseCode = "404", description ="Pedido não encontrado."),
	})
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id,
			@RequestBody AtualizarStatusRequest request) {
		
		Pedido pedido = pedidoService.atualizarStatus(id, request.getStatus());
		
		return ResponseEntity.ok(pedidoService.converterParaResponse(pedido));
		
	}

}
