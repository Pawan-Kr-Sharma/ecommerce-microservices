package com.pk.ecommerce.payment.service;

public interface RazorpayWebhookService {

	public void processWebhook(String signature, String payload);
}
