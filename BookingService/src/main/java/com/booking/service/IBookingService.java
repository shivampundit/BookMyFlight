package com.booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booking.model.Booking;

@Service
public interface IBookingService {

	void deleteBooking(Long bookingId);

	List<Booking> getAllBookings();

	ResponseEntity<String> createBooking(Booking booking);

	ResponseEntity<Booking> getBookingById(Long bookingId);

	ResponseEntity<String> updateBooking(Long bookingId, Booking updatedBooking);

}
