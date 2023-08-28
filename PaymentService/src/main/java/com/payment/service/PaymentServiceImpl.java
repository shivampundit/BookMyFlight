package com.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payment.dao.PaymentRepository;
import com.payment.exception.PaymentNotFoundException;
import com.payment.model.Payment;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public ResponseEntity<String> createPayment(Payment payment) {
		paymentRepository.save(payment);
		return ResponseEntity.status(HttpStatus.CREATED).body("Payment created successfully");
	}

	@Override
	public ResponseEntity<Payment> getPaymentById(Long paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if (payment.isPresent()) {
			return ResponseEntity.ok(payment.get());
		} else {
			throw new PaymentNotFoundException("Payment not found");
		}
	}

	@Override
	public List<Payment> getAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}

	@Override
	public ResponseEntity<String> updatePayment(Long paymentId, Payment updatedPayment) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if (payment.isPresent()) {
			updatedPayment.setTransacrionId(paymentId);
			paymentRepository.save(updatedPayment);
			return ResponseEntity.ok("Payment updated successfully");
		} else {
			throw new PaymentNotFoundException("Payment not found");
		}
	}

	@Override
	public void deletePayment(Long paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if (payment.isPresent()) {
			paymentRepository.delete(payment.get());
		} else {
			throw new PaymentNotFoundException("Payment not found");
		}
	}

}
