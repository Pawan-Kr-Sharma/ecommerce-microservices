package com.pk.ecommerce.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	public NewTopic orderTopic() {
		return TopicBuilder
				.name("order-event").partitions(3).replicas(1).build(); //in Local replicas=1 but production we should have min replicas=3
	}
}
