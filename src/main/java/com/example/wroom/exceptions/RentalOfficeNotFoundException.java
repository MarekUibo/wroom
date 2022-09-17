package com.example.wroom.exceptions;

import com.example.wroom.models.RentalOffice;

import java.util.UUID;

/**
 * Exception for the rental office not found by ID
 *
 * @author Rigottier Jonathan
 */
public class RentalOfficeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public RentalOfficeNotFoundException(UUID id) {
        super(String.format("Rental Office not found for id: %s", id));
    }

    public RentalOfficeNotFoundException(String name) {
        super(String.format("Rental Office not found for address: %s", name));
    }
}