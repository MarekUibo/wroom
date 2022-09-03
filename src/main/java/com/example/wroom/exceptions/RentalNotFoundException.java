package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for rental not found by ID
 *
 * @author Rigottier Jonathan
 */
public class RentalNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public RentalNotFoundException(UUID id) {
        super(String.format("Rental not found for id: %s", id));
    }

    public RentalNotFoundException(Person email) {
        super(String.format("Rental not found for email: %s", email));
    }
}
