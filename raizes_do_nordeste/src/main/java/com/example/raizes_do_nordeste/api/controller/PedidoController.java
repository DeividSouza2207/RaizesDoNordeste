package com.example.raizes_do_nordeste.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido){
	
		Pedido novoPedido = pedidoService.criarPedido(pedido);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(novoPedido);
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listarTodos() {
		
		return ResponseEntity.ok(pedidoService.listarTodos());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		
		return ResponseEntity.ok(pedidoService.buscarPorId(id));
	}

}
