package com.example.wroom.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Auditable model
 *
 * @param <V> Generic type
 * @author Vinod John
 * @implNote This model can be extended to any entity which needs auditing
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<V> {
    @CreatedBy
    @JsonIgnore
    @Column(updatable = false)
    protected V createdBy;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @JsonIgnore
    protected V lastModifiedBy;

    @LastModifiedDate
    @JsonIgnore
    protected LocalDateTime lastModifiedDate;
}

