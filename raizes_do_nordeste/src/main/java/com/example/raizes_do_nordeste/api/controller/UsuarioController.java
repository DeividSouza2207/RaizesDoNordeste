package com.example.raizes_do_nordeste.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.application.service.UsuarioService;
import com.example.raizes_do_nordeste.domain.entity.Usuario;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(
			@RequestBody Usuario usuario) {
		
		Usuario usuarioCadastrado = usuarioService.cadastrarCliente(usuario);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(usuarioCadastrado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		
		Usuario usuario = usuarioService.buscarPorId(id);
		
		return ResponseEntity.ok(usuario);
		
		
	}


}
