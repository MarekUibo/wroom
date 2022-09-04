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
public class Booking {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private String dateOfBooking;
    private String customer;
    private String car;
    private String dateFrom;
    private String dateTo;
    private String rentalBranch;
    private String returnBranch;
    private String amount;
    private boolean isActive;

}
