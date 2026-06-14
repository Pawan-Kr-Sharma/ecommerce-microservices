package com.pk.ecommerce.payment.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.payment.client.InventoryClient;
import com.pk.ecommerce.payment.client.OrderClient;
import com.pk.ecommerce.payment.entity.Payment;
import com.pk.ecommerce.payment.enums.PaymentStatus;
import com.pk.ecommerce.payment.repository.PaymentRepository;
import com.pk.ecommerce.payment.service.RazorpayWebhookService;
import com.razorpay.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RazorpayWebhookServiceImpl implements RazorpayWebhookService {

	private final PaymentRepository paymentRepository;
	private final OrderClient orderClient;
	private final InventoryClient inventoryClient;
	
	@Value("${razorpay.webhook.secret}")
	private String webhookSecret;
	
	
	@Override
	public void processWebhook(String signature, String payload) {
		
		verifySignature(signature,payload);
		
		JSONObject event = new JSONObject(payload);
		String eventType = event.getString("event");
		log.info("Received RazorpayWebhook event {}", eventType);
		
		switch(eventType) {
		
		case "payment.captured" -> handlePaymentSuccess(event);
		
		case "payment.failed" -> handlePaymentFailure(event);
		
		default -> log.warn("unhandled razorpay event ", eventType);
			//throw new IllegalArgumentException("Unexpected value: "+ eventType);
		}

	}

	private void handlePaymentSuccess(JSONObject event) {
		
		JSONObject paymentEntity = extractPaymentEntity(event);
		String razorpayOrderId = paymentEntity.getString("order_id");
		String razorpayPaymentId = paymentEntity.getString("id");
		
		Payment payment = paymentRepository.findByTransactionId(razorpayOrderId).orElseThrow(() -> new IllegalStateException("Payment not found for Razorpay orderId"));
		payment.setPaymenntStatus(PaymentStatus.SUCCESS);
		payment.setGatewayPaymentId(razorpayPaymentId);
		
		//success order
		orderClient.confirmOrder(payment.getOrderId());
		inventoryClient.commitInventory(payment.getOrderId());
	}
	
	private void handlePaymentFailure(JSONObject event) {
		
		JSONObject paymentEntity = extractPaymentEntity(event);
		String razorpayOrderId = paymentEntity.getString("order_id");
		
		Payment payment = paymentRepository.findByTransactionId(razorpayOrderId).orElseThrow(() -> new IllegalStateException("Payment not found for Razorpay orderId"));
		payment.setPaymenntStatus(PaymentStatus.FAILED);
		
		//rollback
		inventoryClient.rollbackInventory(payment.getOrderId());
		orderClient.failOrder(payment.getOrderId());
	}
	
	private JSONObject extractPaymentEntity(JSONObject event) {
		
		return event.getJSONObject("payload")
				.getJSONObject("payment")
				.getJSONObject("entity");
	}
	
	private void verifySignature(String signature, String payload) {
		 try {
			Utils.verifySignature(payload, signature, webhookSecret);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Invalid razorpay webhook signature");
			throw new SecurityException("Invalid Razorpay Webhook Signature");
		}
		
	}

}
