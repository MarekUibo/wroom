package com.example.wroom.exceptions;

import java.util.UUID;

/**
 * Exception for Car not found by ID
 *
 * @author Rigottier Jonathan
 */
public class CarNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public CarNotFoundException(UUID id) {
        super(String.format("Car not found for id: %s", id));
    }

    public CarNotFoundException(String carRegistrationNumber) {
        super(String.format("Car not found for car registration number: %s", carRegistrationNumber));
    }
}
