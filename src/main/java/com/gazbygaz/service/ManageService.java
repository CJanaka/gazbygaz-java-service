package com.gazbygaz.service;


import com.gazbygaz.request.CustomerRequest;
import com.gazbygaz.response.CustomerResponse;

public interface ManageService {
    CustomerResponse manageCustomer(CustomerRequest customerRequest);
}
