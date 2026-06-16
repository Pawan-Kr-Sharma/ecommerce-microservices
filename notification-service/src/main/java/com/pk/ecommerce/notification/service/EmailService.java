package com.pk.ecommerce.notification.service;

public interface EmailService {

	void sendMail(String to, String subject, String body);
		
}
