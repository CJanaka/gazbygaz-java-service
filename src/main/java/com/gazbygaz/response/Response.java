package com.gazbygaz.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private String status;
    private String message;
    private HttpStatus code;
    private String messageDescription;
}
