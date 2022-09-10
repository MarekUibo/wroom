package com.example.wroom.models;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author:Marek Uibo
 */


@Data
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isActive;
}
