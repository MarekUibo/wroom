package com.example.wroom.exceptions;

import java.util.UUID;

/**
 * Exception for branch not found by ID
 *
 * @author Rigottier Jonathan
 */
public class BranchNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public BranchNotFoundException() {
        super("No active branch found");
    }
    public BranchNotFoundException(UUID id) {
        super(String.format("Branch not found for id: %s", id));
    }

    public BranchNotFoundException(String address) {
        super(String.format("Branch not found for address: %s", address));
    }
}