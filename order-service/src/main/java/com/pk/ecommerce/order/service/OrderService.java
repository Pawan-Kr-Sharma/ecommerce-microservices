package com.pk.ecommerce.order.service;

import com.pk.ecommerce.order.dto.OrderRequest;
import com.pk.ecommerce.order.dto.OrderResponse;

public interface OrderService {

	OrderResponse placeOrder(OrderRequest request);
}
