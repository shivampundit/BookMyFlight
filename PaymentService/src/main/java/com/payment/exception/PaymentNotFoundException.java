package com.payment.exception;

public class PaymentNotFoundException extends RuntimeException {

	public PaymentNotFoundException() {
		super();
	}

	public PaymentNotFoundException(String message) {
		super(message);
	}

}
