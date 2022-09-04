package com.example.wroom.models;
/**
 * @author:Marek Uibo
 */
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class RentalEnd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID employeeId;
    private String dateOfReturn;
    private String booking;
    private String additionalPayment;
    private String comments;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Person person;
}
