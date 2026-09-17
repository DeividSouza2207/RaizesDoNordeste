package com.example.raizes_do_nordeste.api.dto;

import com.example.raizes_do_nordeste.domain.enums.Role;

public class CriarUsuarioRequest {
	
	private String nome;
	private String email;
	private String senha;
	private boolean consentimentoLGPD;
	private Role role;
	
	public CriarUsuarioRequest() {
		
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
