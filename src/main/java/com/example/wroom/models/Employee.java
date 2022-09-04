package com.example.wroom.models;
/**
 * @author:Marek Uibo
 */
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Data
public class Employee extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID employeeId;
    private String jobPosition; //Employee/Manager
    private UUID branchId;
    private String branch;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Person person;
}
