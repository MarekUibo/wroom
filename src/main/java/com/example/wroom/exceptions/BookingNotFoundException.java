package com.example.wroom.exceptions;
import org.apache.catalina.User;

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

    public BookingNotFoundException(User user) {
        super(String.format("Booking not found for user id: %s", user.getUsername()));
    }
}
