package com.example.wroom.exceptions;

import com.example.wroom.models.Person;

import java.util.UUID;

/**
 * Exception for employee not found by ID
 *
 * @author Rigottier Jonathan
 */
public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public EmployeeNotFoundException(UUID id) {
        super(String.format("Employee not found for id: %s", id));
    }

    public EmployeeNotFoundException(Person email) {
        super(String.format("Employee not found for email: %s", email));
    }
}