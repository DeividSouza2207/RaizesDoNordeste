package com.example.raizes_do_nordeste.api.exception;

public class RecursoNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
