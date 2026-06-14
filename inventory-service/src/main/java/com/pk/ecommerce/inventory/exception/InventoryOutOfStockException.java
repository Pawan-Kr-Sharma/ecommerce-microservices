package com.pk.ecommerce.inventory.exception;

public class InventoryOutOfStockException extends RuntimeException {

	public InventoryOutOfStockException(String message) {
		super(message);
	}
}
