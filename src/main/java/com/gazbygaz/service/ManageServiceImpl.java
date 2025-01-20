package com.gazbygaz.service;

import com.gazbygaz.common.MasterData;
import com.gazbygaz.common.Util;
import com.gazbygaz.dto.CustomerDto;
import com.gazbygaz.dto.OutletManagerDto;
import com.gazbygaz.entity.Customer;
import com.gazbygaz.entity.Outlet;
import com.gazbygaz.entity.Role;
import com.gazbygaz.exceptions.InvalidRequestException;
import com.gazbygaz.response.CustomerResponse;
import com.gazbygaz.response.OutletManagerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ManageServiceImpl extends CommonService  implements ManageService{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OutletService outletService;

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

    @Override
    public OutletManagerResponse getOutletManagers(OutletManagerDto outletManagerDto) {
        Pageable pageable = PageRequest.of(outletManagerDto.getPage(), outletManagerDto.getSize());

        Page<Outlet> outlets = outletService.findAll(pageable);
        List<OutletManagerDto> outletManagers = outlets.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        OutletManagerResponse outletManagerResponse = new OutletManagerResponse();
        outletManagerResponse.setOutletManagers(outletManagers);

        return outletManagerResponse;
    }

    private OutletManagerDto mapToDTO(Outlet outlet) {
        OutletManagerDto outletDTO = new OutletManagerDto();
        BeanUtils.copyProperties(outlet, outletDTO);
        outletDTO.setCreatedDatetime(Util.formatDate(outlet.getCreatedDatetime()));
        outletDTO.setModifiedDatetime(Util.formatDate(outlet.getModifiedDatetime()));

        if (outlet.getUser() == null) {
            log.warn("user null or empty. outletId="+outlet.getOutletId());
            return outletDTO;
        }

        outletDTO.setUserId(outlet.getUser().getId());
        outletDTO.setUserName(outlet.getUser().getUsername());

        if (outlet.getUser().getRoles() == null || outlet.getUser().getRoles().isEmpty()) {
            log.warn("user rolls null or empty. outletId="+outlet.getOutletId());
            return outletDTO;
        }

        String roles = outlet.getUser().getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(", ")); // Concatenates roles with commas
        outletDTO.setRole(roles);
        return outletDTO;
    }
}
