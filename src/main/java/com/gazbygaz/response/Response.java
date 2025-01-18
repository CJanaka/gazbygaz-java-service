package com.gazbygaz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String code;
    private String status;
    private String message;
    private String authToken;
    private String messageDescription;
}
