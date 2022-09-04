package com.example.wroom.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author:Marek Uibo
 */
@Entity
@Data
public class Booking {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

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
