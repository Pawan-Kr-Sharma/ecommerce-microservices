package com.pk.ecommerce.order.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceEvent {

	private String eventId;
	private String orderId;
	private String skuCode;
	private Integer quantity;
	private String eventType;
	private LocalDateTime eventTime;
}
