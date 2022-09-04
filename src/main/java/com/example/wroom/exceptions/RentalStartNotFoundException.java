package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for rental not found by ID
 *
 * @author Rigottier Jonathan
 */
public class RentalStartNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public RentalStartNotFoundException(UUID id) {
        super(String.format("Rental not found for id: %s", id));
    }

    public RentalStartNotFoundException(Person email) {
        super(String.format("Rental not found for email: %s", email));
    }
}