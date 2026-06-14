package com.pk.ecommerce.payment.dto;

import com.pk.ecommerce.payment.enums.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusUpdateRequest {

	@NotNull(message = "payment status is required")
	private PaymentStatus paymentStatus;
}
