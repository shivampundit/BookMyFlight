package com.payment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component; 
import com.payment.model.Payment;

@Component
public class PaymentKafkaProducer {
	private static final String TOPIC = "payment-topic";

	@Autowired
	private KafkaTemplate<String, Payment> kafkaTemplate;

	public void sendBookingCreatedEvent(Payment booking) {
		kafkaTemplate.send(TOPIC, booking);
	}
}
