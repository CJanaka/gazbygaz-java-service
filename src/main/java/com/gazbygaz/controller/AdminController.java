package com.gazbygaz.controller;

import com.gazbygaz.common.MasterData;
import com.gazbygaz.request.CustomerRequest;
import com.gazbygaz.response.CustomerResponse;
import com.gazbygaz.service.ManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = MasterData.GAZ_BY_GAZ_V1+"/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private ManageService manageService;

    @PostMapping(value = "customer/update")
    public CustomerResponse manageCustomer(@RequestBody CustomerRequest customerRequest){
        return manageService.manageCustomer(customerRequest);
    }
}
