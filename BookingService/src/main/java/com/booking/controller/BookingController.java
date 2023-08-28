package com.booking.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.exception.BookingNotFoundException;
import com.booking.kafka.BookingKafkaProducer;
import com.booking.model.Booking;
import com.booking.model.Payment;
import com.booking.service.IBookingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private IBookingService bookingService;
	@Autowired
	private BookingKafkaProducer bookingKafkaProducer;

	@PostMapping
	@CircuitBreaker(name = "PaymentService", fallbackMethod = "getDefaultInfo")
	public ResponseEntity<String> createBooking(@RequestBody @Valid Booking booking) {

		bookingService.createBooking(booking);
		bookingKafkaProducer.sendBookingCreatedEvent(booking);
		return ResponseEntity.status(HttpStatus.CREATED).body("Booking Created Successfully");

	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Booking> getBookingById(@PathVariable @Valid Long bookingId) {
		try {
			ResponseEntity<Booking> booking = bookingService.getBookingById(bookingId);
			return ResponseEntity.ok(booking.getBody());
		} catch (BookingNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	@CircuitBreaker(name = "PaymentService", fallbackMethod = "getDefaultInfo")
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getAllBookings();
		Payment payment = new Payment();
		Booking booking = new Booking((long) 11111, "Shivam", "KAnpur", "Bangalore", 23444.0, payment);
		booking.add(linkTo(methodOn(BookingController.class).getBookingById(booking.getId())).withRel("Get By Id"));
		booking.add(linkTo(methodOn(BookingController.class).updateBooking(booking.getId(), booking))
				.withRel("Update Booking"));
		booking.add(linkTo(methodOn(BookingController.class).deleteBooking(booking.getId())).withRel("Delete Booking"));
		booking.add(linkTo(methodOn(BookingController.class).createBooking(booking)).withSelfRel());
		return ResponseEntity.ok(bookings);
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<String> updateBooking(@PathVariable @Valid Long bookingId,
			@RequestBody @Valid Booking updatedBooking) {
		try {
			bookingService.updateBooking(bookingId, updatedBooking);
			bookingKafkaProducer.sendBookingCreatedEvent(updatedBooking);
			return ResponseEntity.status(HttpStatus.CREATED).body("Booking Updated Successfully");
		} catch (BookingNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");

		}
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<String> deleteBooking(@PathVariable @Valid Long bookingId) {
		try {
			bookingService.deleteBooking(bookingId);
//			ResponseEntity.ok("Booking deleted successfully");
			bookingKafkaProducer.sendBookingCreatedEvent(bookingService.getBookingById(bookingId).getBody());
			return ResponseEntity.status(HttpStatus.OK).body("Booking Deleted Successfully");
		} catch (BookingNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
		}
	}

	public String getDefaultInfo() {
		return "Calling Payment Service Failed!";
	}

}
