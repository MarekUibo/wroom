package com.example.wroom.exceptions;

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

}
