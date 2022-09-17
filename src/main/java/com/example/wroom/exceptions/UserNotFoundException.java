package com.example.wroom.exceptions;

import java.util.UUID;

/**
 * Exception for user not found
 *
 * @author Kristiina Lindre
 */
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String email) {
        super(String.format("User not found for email: %s!", email));
    }

    public UserNotFoundException(String userName, String password) {
        super(String.format("User not found for username=%s and the given password!", userName));
    }

    public UserNotFoundException(UUID id) {
        super(String.format("User not found for id=%s!", id));
    }

}
