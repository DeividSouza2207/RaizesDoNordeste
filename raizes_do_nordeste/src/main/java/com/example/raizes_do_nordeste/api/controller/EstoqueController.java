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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping({"/estoques"})
@SecurityRequirement(name = "bearerAuth")
public class EstoqueController {
	
	private final EstoqueService estoqueService;
	
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}
	@Operation(
			summary = "Cadastra um estoque",
			description = "Cadastra a quantidades de produtos disponível na unidade.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Estoque cadastrado com sucesso."),
		@ApiResponse(responseCode = "400", description ="Quantidade ou dados incorretos."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	
	})
	
	@PostMapping
	public ResponseEntity<Estoque> criar(@RequestBody Estoque estoque){
		Estoque novoEstoque = estoqueService.salvar(estoque);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(novoEstoque);
	}
	
	@Operation(
			summary = "Lista os estoques",
			description = "Mostra os estoques cadastrados nas unidades.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Estoque encontrado."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
	
	})
	
	@GetMapping
	public ResponseEntity<List<Estoque>> listarTodos(){
		
		List<Estoque> estoques = estoqueService.listarTodos();
		return ResponseEntity.ok(estoques);
	}
	
	@Operation(
			summary = "Encontra um estoque por ID",
			description = "Mostra os dados de um estoque específico.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Estoque encontrado."),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado."),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão."),
		@ApiResponse(responseCode = "404", description ="Estoque não encontrado."),
	})
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> buscarId(@PathVariable Long id){
		
		Estoque estoque = estoqueService.buscarId(id);
		return ResponseEntity.ok(estoque);
	}

}
