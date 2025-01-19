package com.gazbygaz.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedDate
    @Column(name = "created_datetime")
    private Timestamp createdDatetime;

    @LastModifiedDate
    @Column(name = "modified_datetime")
    private Timestamp modifiedDatetime;

    @Version
    @Column(name = "version")
    private Integer version;
}
