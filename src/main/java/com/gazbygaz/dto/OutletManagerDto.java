package com.gazbygaz.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OutletManagerDto extends Pagination{

    private Long userId;
    private String outletName;
    private String userName;
    private String email;
    private String phone;
    private String role;
    private String createdDatetime;
    private String modifiedDatetime;
}
