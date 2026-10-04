package com.example.raizes_do_nordeste.api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroResponse> tratarRecursoNaoEncontrado(
			RecursoNaoEncontradoException exception,
			ServletWebRequest request) {
		
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(),
				request.getRequest().getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
	
	@ExceptionHandler(RegraDeNegocioException.class)
	public ResponseEntity<ErroResponse> tratarRegraDeNegocio(
			RegraDeNegocioException exception,
			ServletWebRequest request) {
		
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(),
				request.getRequest().getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
		
	}
	
	@ExceptionHandler(CredenciaisInvalidasException.class)
	public ResponseEntity<ErroResponse> tratarCredenciaisInvalidas(
			CredenciaisInvalidasException exception,
			ServletWebRequest request) {
		
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				exception.getMessage(),
				request.getRequest().getRequestURI());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> tratarValidacao(
			MethodArgumentNotValidException exception) {
		
		Map<String, String>campos = new HashMap<>();
		
		exception.getBindingResult()
					.getFieldErrors()
					.forEach(error ->
							campos.put(error.getField(), error.getDefaultMessage())
							);
		
		Map<String, Object> resposta = new HashMap<>();
		
		resposta.put("status", 400);
		resposta.put("error", "Bad Request");
		resposta.put("message", "Erro de Validação");
		resposta.put("campos", campos);
		
		return ResponseEntity.badRequest().body(resposta);
		
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErroResponse> tratarJsonInvalido(
			HttpMessageNotReadableException exception,
			ServletWebRequest request) {
		
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Dados inválidos",
				request.getRequest().getRequestURI());
		
		return ResponseEntity.badRequest().body(erro);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResponse> tratarErroInterno(
			Exception exception,
			ServletWebRequest request) {
		
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				"Houve um erro interno no servidor.",
				request.getRequest().getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

}
