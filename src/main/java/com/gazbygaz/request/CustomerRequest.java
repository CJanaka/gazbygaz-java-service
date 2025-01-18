package com.gazbygaz.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private long customerId;
    private String phone;
    private String email;
}
