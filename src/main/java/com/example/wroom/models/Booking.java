package com.example.wroom.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
//import java.util.Date;
import java.util.UUID;
import java.util.Calendar;

/**
 * @author Marek Uibo
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Booking extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private BigDecimal additionalPayment;
    private String comments;
    private BigDecimal amount;
    private boolean isActive;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @OneToOne(cascade = CascadeType.MERGE)
    private Car car;

    @OneToOne(cascade = CascadeType.MERGE)
    private Branch rentalBranch;

    @OneToOne(cascade = CascadeType.MERGE)
    private Branch returnBranch;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

}
