package com.example.wroom.exceptions;

import java.util.UUID;

/**
 * Exception for authority not found
 *
 * @author Kristiina Lindre
 */
public class AuthorityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public AuthorityNotFoundException(String name) {
        super(String.format("Authority not found for name: %s", name));
    }

    public AuthorityNotFoundException(UUID id) {
        super(String.format("Authority not found for id: %s", id));
    }

}
