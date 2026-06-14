package com.pk.ecommerce.payment.service.impl;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.payment.dto.PaymentResponse;
import com.pk.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.pk.ecommerce.payment.dto.RequestPayment;
import com.pk.ecommerce.payment.entity.Payment;
import com.pk.ecommerce.payment.enums.PaymentStatus;
import com.pk.ecommerce.payment.exception.PaymentNotFoundException;
import com.pk.ecommerce.payment.repository.PaymentRepository;
import com.pk.ecommerce.payment.service.PaymentService;
import com.pk.ecommerce.payment.util.TransactionGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{

	private final PaymentRepository paymentRepository;
	
	@Override
	public PaymentResponse createPayment(RequestPayment request) {
		
		Payment payment = Payment.builder()
		.orderId(request.getOrderId())
		.amount(request.getAmount())
		.paymentMethod(request.getPaymentMethod())
		.paymenntStatus(PaymentStatus.PENDING)
		.transactionId(TransactionGenerator.generate())
		.build();
		
		Payment savedPayment = paymentRepository.save(payment);
		
		return mapToResponse(savedPayment);
	}

	@Override
	public PaymentResponse getPaymentByOrderId(String orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(() -> new PaymentNotFoundException("Payment not found for this OrderId: "+ orderId));
		return mapToResponse(payment);
	}

	@Override
	public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatusUpdateRequest request) {
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: "+ paymentId));
		payment.setPaymenntStatus(request.getPaymentStatus());
		paymentRepository.save(payment);
		return mapToResponse(payment);
	}

	private PaymentResponse mapToResponse(Payment payment) {
		return new PaymentResponse(
				payment.getId(),
				payment.getOrderId(),
				payment.getAmount(),
				payment.getPaymenntStatus(),
				payment.getTransactionId()
				);
	}
}
