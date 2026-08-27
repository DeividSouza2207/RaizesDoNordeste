package com.example.raizes_do_nordeste.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.api.dto.LoginRequest;
import com.example.raizes_do_nordeste.api.dto.LoginResponse;
import com.example.raizes_do_nordeste.domain.entity.Usuario;
import com.example.raizes_do_nordeste.domain.repository.UsuarioRepository;
import com.example.raizes_do_nordeste.security.JwtService;

@Service
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder,
			JwtService jwtService) {
		
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		
	}
	
	public LoginResponse login(LoginRequest request) {
		
		Usuario usuario = usuarioRepository
				.findByEmail(request.getEmail())
				.orElseThrow(() ->
						new RuntimeException("E-mail ou senha inválidos"));
		
		if(!usuario.isAtivo()) {
			throw new RuntimeException("Usuário inativo");
		}
		
		boolean senhaCorreta = passwordEncoder.matches(request.getSenha(),
													   usuario.getSenha());
		
		if(!senhaCorreta) {
			throw new RuntimeException("E-mail ou senha inválidos");
		}
		
		String token = jwtService.gerarToken(usuario);
		
		return new LoginResponse(
				token,"Bearer",
				usuario.getId(),
				usuario.getNome(),
				usuario.getRole().name());
		
	}
}
