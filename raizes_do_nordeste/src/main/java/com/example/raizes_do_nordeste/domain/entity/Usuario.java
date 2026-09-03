package com.example.raizes_do_nordeste.domain.entity;

import java.time.LocalDateTime;

import com.example.raizes_do_nordeste.domain.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable = false, length=100)
	private String nome;
	
	@Column(name="email", nullable = false, length=100)
	private String email;
	
	@Column(name="senha", nullable = false, length=255)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", nullable = false, length=30)
	private Role role;
	
	@Column(name="ativo", nullable = false)
	private boolean ativo = true;
	
	@Column(name="consentimento", nullable = false)
	private boolean consentimentoLGPD = false;
	
	@Column(name = "data_consentimento")
	private LocalDateTime dataConsentimento;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public LocalDateTime getDataConsentimento() {
		return dataConsentimento;
	}

	public void setDataConsentimento(LocalDateTime dataConsentimento) {
		this.dataConsentimento = dataConsentimento;
	}

	
	

}