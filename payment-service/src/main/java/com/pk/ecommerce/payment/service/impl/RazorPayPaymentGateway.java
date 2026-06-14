package com.pk.ecommerce.payment.service.impl;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.payment.config.RazorPayConfig;
import com.pk.ecommerce.payment.dto.GatewayOrderResponse;
import com.pk.ecommerce.payment.service.PaymentGateway;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RazorPayPaymentGateway implements PaymentGateway{
	
	private final RazorpayClient razorpayClient;
	
	@Override
	public GatewayOrderResponse createOrder(String orderId, BigDecimal amount) {
		
		try {
			
			JSONObject options = new JSONObject(); //razorpay taking JSON 
			options.put("amount", amount.multiply(BigDecimal.valueOf(100)));  //rupess convert into paisa
			options.put("currency", "INR");
			options.put("receipt", orderId);
			
			Order order = razorpayClient.orders.create(options); //ths order razor pay client
			
			return new GatewayOrderResponse(
					order.get("id"), 
					order.get("currency"), 
					amount, 
					order.get("status"), 
					"RAZORPAY"
					);
			
		} catch (Exception e) {
			throw new IllegalStateException("RazorpayOrder Create failed");
		}
		
	}

}
