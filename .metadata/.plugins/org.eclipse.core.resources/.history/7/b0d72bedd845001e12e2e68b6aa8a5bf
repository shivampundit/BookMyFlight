package com.payment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.payment.model.Booking;
import com.payment.model.Payment;
import com.payment.service.IPaymentService;

public class BookingKafkaConsumer {

	@Autowired
	private IPaymentService paymentService;

	@KafkaListener(topics = "booking-topic", groupId = "payment-service")
	public void consumeBookingEvent(Booking booking) {
		// Logic to process booking event and perform payment operations

		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setPaymentMode("Credit Card");
		payment.setAmount(booking.getAmount());

		paymentService.createPayment(payment);
	}
}
