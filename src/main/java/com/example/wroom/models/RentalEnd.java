package com.example.wroom.models;
/**
 * @author:Marek Uibo
 */
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class RentalEnd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID employeeId;
    private String dateOfReturn;
    private String booking;
    private String additionalPayment;
    private String comments;
}
