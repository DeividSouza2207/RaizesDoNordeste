package com.example.raizes_do_nordeste.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.api.dto.LoginRequest;
import com.example.raizes_do_nordeste.api.dto.LoginResponse;
import com.example.raizes_do_nordeste.application.service.AuthService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		
		LoginResponse resposta = authService.login(request);
		
		return ResponseEntity.ok(resposta);
	}

}
