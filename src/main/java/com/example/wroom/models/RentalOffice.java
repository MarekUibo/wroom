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
public class RentalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String internetDomain;
    private String contactAddress;
    private String owner;
    private String logotype;
    private List<Branch> branches;
    private boolean isActive;
}
