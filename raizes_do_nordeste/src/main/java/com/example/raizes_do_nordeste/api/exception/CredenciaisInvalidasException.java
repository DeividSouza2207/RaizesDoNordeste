package com.example.raizes_do_nordeste.api.exception;

public class CredenciaisInvalidasException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public CredenciaisInvalidasException(String mensagem) {
		super(mensagem);
	}

}
