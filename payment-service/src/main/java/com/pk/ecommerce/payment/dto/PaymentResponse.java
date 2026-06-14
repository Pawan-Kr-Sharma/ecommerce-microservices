package com.pk.ecommerce.payment.dto;

import java.math.BigDecimal;

import com.pk.ecommerce.payment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

	private Long paymentId;
	private String orderId;
	private BigDecimal amount;
	private PaymentStatus paymentStatus;
	private String tansactionId;
}
