package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for booking not found by ID
 *
 * @author Rigottier Jonathan
 */
public class BookingNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public BookingNotFoundException(UUID id) {
        super(String.format("Booking not found for id: %s", id));
    }

    public BookingNotFoundException(Person email) {
        super(String.format("Customer not found for email: %s", email));
    }
}
