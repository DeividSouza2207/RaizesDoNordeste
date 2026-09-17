package com.example.raizes_do_nordeste.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.api.dto.CriarUsuarioRequest;
import com.example.raizes_do_nordeste.api.dto.UsuarioResponse;
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
	public ResponseEntity<UsuarioResponse> cadastrar(
			@RequestBody CriarUsuarioRequest request) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		usuario.setConsentimentoLGPD(request.isConsentimentoLGPD());
		usuario.setRole(request.getRole());
		
		
		Usuario usuarioCadastrado = usuarioService.cadastrarCliente(usuario);
		
		UsuarioResponse response = new UsuarioResponse(
				usuarioCadastrado.getId(),
				usuarioCadastrado.getNome(),
				usuarioCadastrado.getEmail(),
				usuarioCadastrado.isAtivo(),
				usuarioCadastrado.isConsentimentoLGPD(),
				usuarioCadastrado.getRole()
			);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}
	
	 @GetMapping
	    public ResponseEntity<List<Usuario>> listar() {

	        return ResponseEntity.ok(
	                usuarioService.listarTodos()
	        );
	    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		
		Usuario usuario = usuarioService.buscarPorId(id);
		
		return ResponseEntity.ok(usuario);
		
		
	}


}
