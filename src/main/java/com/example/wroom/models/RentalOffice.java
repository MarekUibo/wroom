package com.example.wroom.models;
/**
 * @author Marek Uibo
 */

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Data
public class RentalOffice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private String internetDomain;
    private String contactAddress;

    @OneToOne(cascade = CascadeType.MERGE)
    private Employee owner;

    private String logoUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Branch> branches;

    private BigDecimal sumOfAmountsForCarRental;
    private boolean isActive;
}
