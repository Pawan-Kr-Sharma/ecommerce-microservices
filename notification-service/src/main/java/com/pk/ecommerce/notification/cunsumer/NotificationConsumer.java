package com.pk.ecommerce.notification.cunsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pk.ecommerce.notification.dto.NotificationEvent;
import com.pk.ecommerce.notification.service.EmailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

	private final EmailService emailService;
	
	@KafkaListener(topics = "Notification-topic", groupId = "notification-group")
	public void consume(NotificationEvent event) {
		emailService.sendMail(event.getEmail(), event.getSubject(), event.getMessage());
	}
	
	
}
