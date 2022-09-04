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
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sumOfAmountsForCarRental;
    private boolean isActive;
}
