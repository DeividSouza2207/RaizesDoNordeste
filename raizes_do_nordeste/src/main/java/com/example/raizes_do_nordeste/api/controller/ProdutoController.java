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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping({"/produtos"})
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@Operation(
			summary = "Cadastra um produto",
			description = "Cadastra um produto novo no sistema")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Produto cadastrado com sucesso"),
		@ApiResponse(responseCode = "400", description ="Dados inválidos."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	})
	
	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto){
		Produto novoProduto = produtoService.salvar(produto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(novoProduto);
	}
	
	@Operation(
			summary = "Lista os produtos",
			description = "Mostra os produtos cadastrados")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Produto encontrado"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	})
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos(){
		
		List<Produto> produtos = produtoService.listarTodos();
		return ResponseEntity.ok(produtos);
	}

	@Operation(
			summary = "Encontra um produto pelo ID",
			description = "Mostra os dados de um produto específico.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Produto encontrado"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão"),
		@ApiResponse(responseCode = "404", description ="Produto não encontrado"),
	})
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarId(@PathVariable Long id){
		
		Produto produto = produtoService.buscarId(id);
		return ResponseEntity.ok(produto);
	}
}
