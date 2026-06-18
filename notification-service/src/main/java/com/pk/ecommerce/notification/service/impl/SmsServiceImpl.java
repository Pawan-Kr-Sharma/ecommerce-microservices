package com.pk.ecommerce.notification.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.notification.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

	@Value("${twilio.from-number}")
	private String fromNumber;
	
	@Override
	public void sendSms(String to, String message) {
		Message.creator(new PhoneNumber(to), new PhoneNumber(fromNumber), message).create();
		
	}

}
