package com.example.wroom.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * Authority for User
 *
 * @author Kristiina Lindre
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Authority extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private boolean isActive;
}
