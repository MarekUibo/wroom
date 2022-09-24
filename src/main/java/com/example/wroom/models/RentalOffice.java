package com.example.wroom.models;
/**
 * @author Marek Uibo
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RentalOffice extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private String internetDomain;

    @OneToOne(cascade = CascadeType.MERGE)
    private User owner;

    private String logoUrl;
    private String fullAddress;
    private String phoneNumber;
    private String email;
    private String city;
    private BigDecimal sumOfAmountsForCarRental;
    private boolean isActive;
}
