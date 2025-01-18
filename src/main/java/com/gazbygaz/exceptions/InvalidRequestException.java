package com.gazbygaz.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InvalidRequestException extends RuntimeException {

    private HttpStatus status;
    private String code;
    private String message;
    private String messageDescription;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidRequestException(String errcode, String errmsg) {
        super(errmsg);
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.code = errcode;
        this.message = errmsg;
    }
    
    public InvalidRequestException(String errcode, String errmsg, String errmsgInfo) {
        super(errmsg);
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.code = errcode;
        this.message = errmsg;
        this.messageDescription = errmsgInfo;
    }
}
