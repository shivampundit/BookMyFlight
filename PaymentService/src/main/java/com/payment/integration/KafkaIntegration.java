package com.payment.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaIntegration {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendEvent(String topic, Object event) {
		kafkaTemplate.send(topic, event);
	}
}
