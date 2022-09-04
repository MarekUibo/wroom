package com.example.wroom.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author:Marek Uibo
 */

@Entity
@Data
public class RentalStart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employee;
    private String rentalDate;
    private String booking;
    private String comments;
    private boolean isActive;
}
