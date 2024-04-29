package com.app.mvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDate creation_date;

    @CreatedBy
    @Column(updatable = false)
    private String created_by;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate modification_date;

    @LastModifiedBy
    @Column(insertable = false)
    private String updated_by;
}
