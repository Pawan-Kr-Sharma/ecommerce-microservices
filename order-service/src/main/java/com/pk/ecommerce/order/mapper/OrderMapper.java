package com.pk.ecommerce.order.mapper;

import com.pk.ecommerce.order.dto.OrderRequest;
import com.pk.ecommerce.order.dto.OrderResponse;
import com.pk.ecommerce.order.entity.Order;

public class OrderMapper {

	public Order toEntity(OrderRequest request) {
		Order order = new Order();
		order.setSkuCode(request.getSkuCode());
		order.setQuantity(request.getQuantity());
		order.setPrice(request.getPrice());
		return order;
	}
	
	public OrderResponse toResponse(Order order) {
		return new OrderResponse(
				order.getOrderNumber(), 
				order.getOrderStatus());
	}
}
