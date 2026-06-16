package com.pk.ecommerce.notification.dto;

import lombok.Data;

@Data
public class NotificationEvent {
	private String email;
	private String subject;
	private String message;
}
