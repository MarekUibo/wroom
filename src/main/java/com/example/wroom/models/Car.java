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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String carRegistrationNumber;
    private String mark;
    private String model;
    private String bodyType;
    private String yearOfProduction;
    private String color;
    private String mileage;
    private String status;
    private String amount;

}
