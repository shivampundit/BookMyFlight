package com.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.booking.dao.BookingRepository;
import com.booking.exception.BookingNotFoundException;
import com.booking.model.Booking;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public ResponseEntity<String> createBooking(Booking booking) {
		bookingRepository.save(booking);
		return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
	}

	@Override
	public ResponseEntity<Booking> getBookingById(Long bookingId) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if (booking.isPresent()) {
			return ResponseEntity.ok(booking.get());
		} else {
			throw new BookingNotFoundException("Booking not found");
		}
	}

	@Override
	public List<Booking> getAllBookings() {
		return (List<Booking>) bookingRepository.findAll();
	}

	@Override
	public ResponseEntity<String> updateBooking(Long bookingId, Booking updatedBooking) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if (booking.isPresent()) {
			updatedBooking.setId(bookingId);
			bookingRepository.save(updatedBooking);
			return ResponseEntity.ok("Booking updated successfully");
		} else {
			throw new BookingNotFoundException("Booking not found");
		}
	}

	@Override
	public void deleteBooking(Long bookingId) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if (booking.isPresent()) {
			bookingRepository.delete(booking.get());
		} else {
			throw new BookingNotFoundException("Booking not found");
		}
	}

}
