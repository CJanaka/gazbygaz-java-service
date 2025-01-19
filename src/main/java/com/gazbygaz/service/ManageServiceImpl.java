package com.gazbygaz.service;

import com.gazbygaz.common.MasterData;
import com.gazbygaz.dto.CustomerDto;
import com.gazbygaz.entity.Customer;
import com.gazbygaz.exceptions.InvalidRequestException;
import com.gazbygaz.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManageServiceImpl extends CommonService  implements ManageService{

    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerResponse manageCustomer(CustomerDto customerRequest) {

        Long customerId = customerRequest.getCustomerId();

        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null){
            log.error("customer not found for customerId = "+customerId);
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.name(), "customer not found!");
        }

        if (customerRequest.getEmail() != null && !customerRequest.getEmail().isEmpty()){
            customer.setEmail(customerRequest.getEmail());
        }

        if (customerRequest.getPhone() != null && !customerRequest.getPhone().isEmpty()){
            customer.setEmail(customerRequest.getPhone());
        }

        customerService.saveCustomer(customer);
        log.info("customer updated! = "+customerId);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCode(String.valueOf(HttpStatus.OK.value()));
        customerResponse.setStatus(HttpStatus.OK.name());
        customerResponse.setMessage(getMessageForResponseCode(MasterData.ResponseCode.CUS_UPDATE_SUCCESS_CODE, null));

        return customerResponse;
    }

}
