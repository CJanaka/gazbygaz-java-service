package com.gazbygaz.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InvalidRequestException extends RuntimeException {

    private String status;
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
        this.setStatus(HttpStatus.BAD_REQUEST.name());
        this.code = errcode;
        this.message = errmsg;
    }

    public InvalidRequestException(String status, String errcode, String errmsg) {
        super(errmsg);
        this.setStatus(status);
        this.code = errcode;
        this.message = errmsg;
    }
    
    public InvalidRequestException(String status, int errcode, String errmsg, String errmsgInfo) {
        super(errmsg);
        this.setStatus(status);
        this.code = String.valueOf(errcode);
        this.message = errmsg;
        this.messageDescription = errmsgInfo;
    }
}
