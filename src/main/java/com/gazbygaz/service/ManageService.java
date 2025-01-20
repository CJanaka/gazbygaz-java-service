package com.gazbygaz.service;


import com.gazbygaz.dto.CustomerDto;
import com.gazbygaz.dto.OutletManagerDto;
import com.gazbygaz.response.CustomerResponse;
import com.gazbygaz.response.OutletManagerResponse;

public interface ManageService {
    CustomerResponse manageCustomer(CustomerDto customerRequest);
    OutletManagerResponse getOutletManagers(OutletManagerDto outletManagerDto);
}
