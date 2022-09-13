package com.example.wroom.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Marek Uibo
 */


@Entity
@Data
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String registrationNumber;
    private String mark;
    private String model;

    @Enumerated(EnumType.STRING)
    private CarBodyType bodyType;

    private int yearOfProduction;
    private String color;
    private Long mileage;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @OneToOne(cascade = CascadeType.MERGE)
    private Branch homeBranch;

    private BigDecimal amount;
    private boolean isActive;
}
