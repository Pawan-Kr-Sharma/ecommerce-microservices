package com.pk.ecommerce.order.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderPlaceEvent {

	private String eventId;
	private String orderId;
	private String skuCode;
	private Integer quantity;
	private String eventType;
	private LocalDateTime eventTime;
}
