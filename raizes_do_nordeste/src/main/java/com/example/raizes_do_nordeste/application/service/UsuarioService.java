package com.example.raizes_do_nordeste.application.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.domain.entity.Usuario;
import com.example.raizes_do_nordeste.domain.enums.Role;
import com.example.raizes_do_nordeste.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	 
	public UsuarioService(UsuarioRepository usuarioRepository,
						   PasswordEncoder passwordEncoder) {
	        this.usuarioRepository = usuarioRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	
	public Usuario cadastrarCliente(Usuario usuario) {
		
		if(usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new RuntimeException("Este e-mail já foi cadastrado");
		}
		
		usuario.setRole(Role.CLIENTE);
		
		String senhaHash = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaHash);
		
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email)
				.orElseThrow(() ->
						new RuntimeException("Usuário não encontradp"));
	}
	
	public Usuario buscarPorId(Long id) {
		
		return usuarioRepository.findById(id)
				.orElseThrow(() ->
						new RuntimeException("Usuário não encontrado"));
	}
}
