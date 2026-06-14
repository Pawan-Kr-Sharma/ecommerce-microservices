package com.pk.ecommerce.payment.service;

import com.pk.ecommerce.payment.dto.PaymentResponse;
import com.pk.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.pk.ecommerce.payment.dto.RequestPayment;

public interface PaymentService {

	PaymentResponse createPayment(RequestPayment request);
	PaymentResponse getPaymentByOrderId(String orderId);
	PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatusUpdateRequest request);
}
