package com.example.raizes_do_nordeste.api.dto;

public class LoginResponse {
	
	private String token;
	private String tipo;
	private Long usuarioId;
	private String nome;
	private String role;
	
	public LoginResponse() {
		
	}

	public LoginResponse(String token, String tipo, Long usuarioId,
						String nome, String role) {
		
	this.token = token;
	this.tipo = tipo;
	this.usuarioId = usuarioId;
	this.nome = nome;
	this.role = role;
	
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
