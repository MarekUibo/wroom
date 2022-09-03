package com.example.wroom.exceptions;

import java.util.UUID;

/**
 * Exception for the person not found by ID
 *
 * @author Rigottier Jonathan
 */
public class PersonNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public PersonNotFoundException(UUID id) {
        super(String.format("Person not found for id: %s", id));
    }

    public PersonNotFoundException(String email) {
        super(String.format("Person not found for email: %s", email));
    }
}

