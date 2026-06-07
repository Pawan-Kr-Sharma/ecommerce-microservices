package com.pk.ecommerce.product.exception;

public class BadRequestApiException extends RuntimeException{

	public BadRequestApiException(String message) {
		super(message);
	}
}
