package com.example.raizes_do_nordeste.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.api.dto.LoginRequest;
import com.example.raizes_do_nordeste.api.dto.LoginResponse;
import com.example.raizes_do_nordeste.application.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@Operation(
			summary = "Realiza o login",
			description = "Autentica o usuário e gera um token JWT que dá acesso aos endpoints protegidos.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description ="Login realizado com sucesso"),
		@ApiResponse(responseCode = "401", description ="E-mail ou senha inválidos")
	})
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		
		LoginResponse resposta = authService.login(request);
		
		return ResponseEntity.ok(resposta);
	}

}
