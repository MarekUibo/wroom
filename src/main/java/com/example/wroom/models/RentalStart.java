package com.example.wroom.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author:Marek Uibo
 */

@Entity
@Data
public class RentalStart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String employee;
    private String rentalDate;
    private String booking;
    private String comments;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Person person;
}
