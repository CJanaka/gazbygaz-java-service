package com.gazbygaz.exceptions;

import com.gazbygaz.response.Response;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class GolbalValidationErrorResponse extends Response {
    private String message;

    private Map<String, String> validationErrors = new HashMap<>();

    public void addValidationError(String fieldName, String errorMessage) {
        validationErrors.put(fieldName, errorMessage);
    }
}
