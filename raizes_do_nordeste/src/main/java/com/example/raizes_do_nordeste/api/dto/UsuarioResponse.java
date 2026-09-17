package com.example.raizes_do_nordeste.api.dto;

import com.example.raizes_do_nordeste.domain.enums.Role;

public class UsuarioResponse {
	
	private Long id;
	private String nome;
	private String email;
	private boolean ativo;
	private boolean consentimentoLGPD;
	private Role role;

	public UsuarioResponse() {
	}
	
	public UsuarioResponse(Long id, String nome, String email, boolean ativo,
							boolean consentimentoLGPD, Role role) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
		this.consentimentoLGPD = consentimentoLGPD;
		this.role = role;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isConsentimentoLGPD() {
		return consentimentoLGPD;
	}

	public void setConsentimentoLGPD(boolean consentimentoLGPD) {
		this.consentimentoLGPD = consentimentoLGPD;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
