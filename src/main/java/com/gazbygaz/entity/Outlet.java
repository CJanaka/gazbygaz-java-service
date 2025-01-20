package com.gazbygaz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Outlet extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outletId;

    private String outletName;
    private String email;
    private String phone;
    @OneToOne
    private User user;

}
