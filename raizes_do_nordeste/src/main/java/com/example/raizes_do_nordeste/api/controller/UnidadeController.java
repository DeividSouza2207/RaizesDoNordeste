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

@RestController
@RequestMapping({"/unidades"})
public class UnidadeController {
	
	private final UnidadeService unidadeService;
	
	public UnidadeController(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade){
		
		Unidade novaUnidade = unidadeService.salvar(unidade);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							  .body(novaUnidade);
	}
	
	@GetMapping
	public ResponseEntity<List<Unidade>> listarTodas(){
		
		List<Unidade> unidades = unidadeService.listarTodas();
		return ResponseEntity.ok(unidades);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> buscarId(@PathVariable Long id){
		
		Unidade unidade = unidadeService.buscarId(id);
		return ResponseEntity.ok(unidade);
	}
}
