package com.pk.ecommerce.payment.dto;

import java.math.BigDecimal;

import com.pk.ecommerce.payment.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPayment {

	@NotNull(message = "Order Id required")
	private String orderId;
	@Positive(message = "correct Amount is required")
	private BigDecimal amount;
	@NotNull(message = "Payment Method required")
	private PaymentMethod paymentMethod;
}
