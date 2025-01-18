package com.gazbygaz.service;

import com.gazbygaz.entity.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(Long id);
}
