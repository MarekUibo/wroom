package com.example.wroom.models;
/**
 * @author:Marek Uibo
 */
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID customerId;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Person person;
}
