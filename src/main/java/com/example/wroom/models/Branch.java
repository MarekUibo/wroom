package com.example.wroom.models;
/**
 * @author:Marek Uibo
 */
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;
    private List<Employee> employee;
    private List <Car> cars;
    private boolean isActive;
}
