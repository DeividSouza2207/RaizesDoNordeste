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

import com.example.raizes_do_nordeste.application.service.EstoqueService;
import com.example.raizes_do_nordeste.domain.entity.Estoque;


@RestController
@RequestMapping({"/estoques"})
public class EstoqueController {
	
	private final EstoqueService estoqueService;
	
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}
	
	@PostMapping
	public ResponseEntity<Estoque> criar(@RequestBody Estoque estoque){
		Estoque novoEstoque = estoqueService.salvar(estoque);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(novoEstoque);
	}
	
	@GetMapping
	public ResponseEntity<List<Estoque>> listarTodos(){
		
		List<Estoque> estoques = estoqueService.listarTodos();
		return ResponseEntity.ok(estoques);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> buscarId(@PathVariable Long id){
		
		Estoque estoque = estoqueService.buscarId(id);
		return ResponseEntity.ok(estoque);
	}

}
