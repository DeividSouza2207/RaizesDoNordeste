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

import com.example.raizes_do_nordeste.application.service.ProdutoService;
import com.example.raizes_do_nordeste.domain.entity.Produto;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto){
		Produto novoProduto = produtoService.salvar(produto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(novoProduto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos(){
		
		List<Produto> produtos = produtoService.listarTodos();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarId(@PathVariable Long id){
		
		Produto produto = produtoService.buscarId(id);
		return ResponseEntity.ok(produto);
	}
}
