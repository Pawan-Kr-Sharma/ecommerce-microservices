package com.pk.ecommerce.order.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.order.dto.OrderRequest;
import com.pk.ecommerce.order.dto.OrderResponse;
import com.pk.ecommerce.order.entity.Order;
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
	@Override
	public OrderResponse placeOrder(OrderRequest request) {
		log.info("Order creating...");
		
		Order order = orderMapper.toEntity(request);
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderStatus("CREATED");
		Order savedOrder = orderRepository.save(order);
		log.info("order saved with order number:{} ",order.getOrderNumber());
		
		return orderMapper.toResponse(savedOrder);
	}

}
