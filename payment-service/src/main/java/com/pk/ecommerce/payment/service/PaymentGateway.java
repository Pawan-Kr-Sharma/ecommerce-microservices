package com.pk.ecommerce.payment.service;

import java.math.BigDecimal;

import com.pk.ecommerce.payment.dto.GatewayOrderResponse;

public interface PaymentGateway {

	GatewayOrderResponse createOrder(String orderId, BigDecimal amount);
}
