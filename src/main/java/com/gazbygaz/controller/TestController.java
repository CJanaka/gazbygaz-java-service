package com.gazbygaz.controller;

import com.gazbygaz.common.MasterData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = MasterData.GAZ_BY_GAZ_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @GetMapping(value = "health/check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Welcome!!.. Gaz by Gaz : "+new Date());
    }
}
