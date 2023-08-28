package com.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
