package com.payment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payment.model.Payment;

@Service
public interface IPaymentService {

	void deletePayment(Long paymentId);

	List<Payment> getAllPayments();

	ResponseEntity<String> createPayment(Payment payment);

	ResponseEntity<Payment> getPaymentById(Long paymentId);

	ResponseEntity<String> updatePayment(Long paymentId, Payment updatedPayment);


}
