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

import com.example.raizes_do_nordeste.application.service.UnidadeService;
import com.example.raizes_do_nordeste.domain.entity.Unidade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping({"/unidades"})
@SecurityRequirement(name = "bearerAuth")
public class UnidadeController {
	
	private final UnidadeService unidadeService;
	
	public UnidadeController(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}
	
	@Operation(
			summary = "Cadastra uma unidade",
			description = "Cadastra uma nova unidade no sistema")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Unidade cadastrada com sucesso"),
		@ApiResponse(responseCode = "400", description ="Dados inválidos"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão"),
	})
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade){
		
		Unidade novaUnidade = unidadeService.salvar(unidade);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							  .body(novaUnidade);
	}
	
	@Operation(
			summary = "Lista as unidades",
			description = "Mostra as unidades cadastradas")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Unidades encontradas"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão"),
	})
	@GetMapping
	public ResponseEntity<List<Unidade>> listarTodas(){
		
		List<Unidade> unidades = unidadeService.listarTodas();
		return ResponseEntity.ok(unidades);
		
	}
	
	@Operation(
			summary = "Busca uma unidade pelo ID",
			description = "Mostra os dados de uma unidade específica")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Unidade encontrada"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão"),
		@ApiResponse(responseCode = "404", description ="Unidade não encontrada"),
	})
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> buscarId(@PathVariable Long id){
		
		Unidade unidade = unidadeService.buscarId(id);
		return ResponseEntity.ok(unidade);
	}
}
