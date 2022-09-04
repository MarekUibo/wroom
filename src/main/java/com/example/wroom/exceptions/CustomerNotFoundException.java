package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for customer not found by ID
 *
 * @author Rigottier Jonathan
 */
public class CustomerNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public CustomerNotFoundException(UUID id) {
        super(String.format("Customer not found for id: %s", id));
    }

    public CustomerNotFoundException(Person email) {
        super(String.format("Customer not found for email: %s", email));
    }
}