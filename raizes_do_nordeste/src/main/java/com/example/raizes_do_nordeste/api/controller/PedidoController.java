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

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	private final PedidoService pedidoService;
	
	public  PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	@PostMapping
	public ResponseEntity<PedidoResponse> criar(@RequestBody CriarPedidoRequest request){
	
		Pedido novoPedido = pedidoService.criarPedido(request);
		
		PedidoResponse response = pedidoService.converterParaResponse(novoPedido);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
		
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoResponse>> listarTodos() {
		
		List<PedidoResponse> response = pedidoService.listarTodos()
				.stream()
				.map(pedidoService::converterParaResponse)
				.toList();
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
		
		Pedido pedido = pedidoService.buscarPorId(id);
		
		return ResponseEntity.ok(pedidoService.converterParaResponse(pedido));
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id,
			@RequestBody AtualizarStatusRequest request) {
		
		Pedido pedido = pedidoService.atualizarStatus(id, request.getStatus());
		
		return ResponseEntity.ok(pedidoService.converterParaResponse(pedido));
		
	}

}
