package com.booking.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.booking.model.Booking;

@Component
public class BookingKafkaProducer {
	private static final String TOPIC = "booking-topic";

	@Autowired
	private KafkaTemplate<String, Booking> kafkaTemplate;

	public void sendBookingCreatedEvent(Booking booking) {
		kafkaTemplate.send(TOPIC, booking);
	}
}
