package com.gazbygaz.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "customers")
public class Customer extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
