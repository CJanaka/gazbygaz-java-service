package com.gazbygaz.service;


import com.gazbygaz.dto.CustomerDto;
import com.gazbygaz.response.CustomerResponse;

public interface ManageService {
    CustomerResponse manageCustomer(CustomerDto customerRequest);
}
