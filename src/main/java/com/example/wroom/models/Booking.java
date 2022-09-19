package com.example.wroom.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Marek Uibo
 */
@Entity
@Data
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDateTime dateOfBooking;

    @OneToOne(cascade = CascadeType.MERGE)
    private Car car;

    private String userName;
    private String registrationNumber;

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    @OneToOne(cascade = CascadeType.MERGE)
    private Branch rentalBranch;

    @OneToOne(cascade = CascadeType.MERGE)
    private Branch returnBranch;

    private BigDecimal amount;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

    private BigDecimal additionalPayment;
    private String comments;

    @OneToOne(cascade = CascadeType.MERGE)
    private User  rentalEmployee;

    @OneToOne(cascade = CascadeType.MERGE)
    private User returnEmployee;

    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
