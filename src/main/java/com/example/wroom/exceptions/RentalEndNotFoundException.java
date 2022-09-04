package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for rental not found by ID
 *
 * @author Rigottier Jonathan
 */
public class RentalEndNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public RentalEndNotFoundException(UUID id) {
        super(String.format("Return not found for id: %s", id));
    }

    public RentalEndNotFoundException(Person email) {
        super(String.format("Return not found for email: %s", email));
    }
}