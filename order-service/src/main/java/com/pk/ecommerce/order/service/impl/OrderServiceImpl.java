package com.pk.ecommerce.order.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.order.client.InventoryFeignClient;
import com.pk.ecommerce.order.dto.InventoryResponse;
import com.pk.ecommerce.order.dto.OrderRequest;
import com.pk.ecommerce.order.dto.OrderResponse;
import com.pk.ecommerce.order.entity.Order;
import com.pk.ecommerce.order.event.OrderPlaceEvent;
import com.pk.ecommerce.order.kafka.OrderEventProducer;
import com.pk.ecommerce.order.mapper.OrderMapper;
import com.pk.ecommerce.order.repository.OrderRepository;
import com.pk.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final InventoryFeignClient inventoryFeignClient;
	private final OrderEventProducer orderEventProducer;
	
	@Override
	public OrderResponse placeOrder(OrderRequest request) {
		log.info("Order creating...");
		
		//feign client
		InventoryResponse inventory = inventoryFeignClient.isInStock(request.getSkuCode());
		if(!inventory.isInStock()) {
			throw new RuntimeException("Product is out of stock");
		}
		
		Order order = orderMapper.toEntity(request);
		String orderId = UUID.randomUUID().toString();
		order.setOrderNumber(orderId);
		order.setOrderStatus("CREATED");
		orderRepository.save(order);
		
		OrderPlaceEvent event = new OrderPlaceEvent();
		event.setEventId(UUID.randomUUID().toString());
		event.setOrderId(orderId);
		event.setSkuCode(request.getSkuCode());
		event.setQuantity(request.getQuantity());
		//event.setEventType("ORDER-PLACED");
		event.setEventTime(LocalDateTime.now());
		
		orderEventProducer.senOrderEvent(event);
		
		return orderMapper.toResponse(order);
	}

}
