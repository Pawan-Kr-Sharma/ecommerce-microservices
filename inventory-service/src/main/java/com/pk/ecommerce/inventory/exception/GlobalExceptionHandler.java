package com.pk.ecommerce.inventory.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InventoryNotFoundException.class)
	public ResponseEntity<Map<String, Object>> inventoryNotFoundException(InventoryNotFoundException ex) {
		Map<String, Object> map = Map.of("error","Not Found", "message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(InventoryOutOfStockException.class)
	public ResponseEntity<Map<String, Object>> inventoryOutOfStockException(InventoryOutOfStockException ex) {
		Map<String, Object> map = Map.of("error","Not Found", "message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
}
