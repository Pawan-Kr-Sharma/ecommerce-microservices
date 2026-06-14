package com.pk.ecommerce.inventory.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.inventory.event.OrderPlaceEvent;
import com.pk.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

	private final InventoryService inventoryService;
	
	@KafkaListener(topics = "order-event", groupId = "inventory-group")
	public void consume(OrderPlaceEvent event) {
		
		inventoryService.updateStock(event);
	}
}
