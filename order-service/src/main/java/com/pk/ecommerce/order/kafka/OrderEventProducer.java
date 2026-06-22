package com.pk.ecommerce.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.order.event.OrderPlaceEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

	private final KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;

	public void senOrderEvent(OrderPlaceEvent event) {
		kafkaTemplate.send("order-event", event.getOrderId().toString(),event); //topic name, key, event
	}
}
