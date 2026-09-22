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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping({"/usuarios"})
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Operation(
			summary = "Cadastra um usuário",
			description = "Cadastra um novo usuário no sitema e estes usuários são cadastrados com o perfil CLIENTE.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description ="Usuário cadastrado com sucesso"),
		@ApiResponse(responseCode = "400", description ="Dados inválidos")
	})
	
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
	
	@Operation(
			summary = "Lista os usuários",
			description = "Mostra os usuários cadastrados. Este acesso só é permitido para GERENTE ou ADMIN.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Usuários encontrados"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão")
	})
	
	 @GetMapping
	    public ResponseEntity<List<Usuario>> listar() {

	        return ResponseEntity.ok(
	                usuarioService.listarTodos()
	        );
	    }
	
	@Operation(
			summary = "Busca um usuário pelo ID",
			description = "Mostra os dados de um usuário específico. Este acesso só é permitido para GERENTE ou ADMIN.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Usuário encontrado"),
		@ApiResponse(responseCode = "401", description ="Usuário não autenticado"),
		@ApiResponse(responseCode = "403", description ="Usuário não tem permissão"),
		@ApiResponse(responseCode = "404", description ="Usuário não encontrado")
	})
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		
		Usuario usuario = usuarioService.buscarPorId(id);
		
		return ResponseEntity.ok(usuario);
		
		
	}


}
