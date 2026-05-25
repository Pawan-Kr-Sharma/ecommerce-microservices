package com.pk.ecommerce.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public  ResponseEntity<Map<String, Object>> resourceAlredyExistsException(ResourceAlreadyExistsException ex) {
		
		Map<String, Object> map = Map.of("error","Conflict", "message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> resourceNotFoundException(ResourceNotFoundException ex) {

		Map<String, Object> map = Map.of("error","Not Found", "message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentValidationException(MethodArgumentNotValidException ex){
		
		Map<String, Object> error = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err -> error.put(err.getField(), err.getDefaultMessage()));
		
		Map<String, Object> body = Map.of("error","Validation Failed", "details",error);
		return ResponseEntity.badRequest().body(body);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleAll(Exception ex){
		
		Map<String, Object> body = Map.of("error","Internal Server Error", "message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}
