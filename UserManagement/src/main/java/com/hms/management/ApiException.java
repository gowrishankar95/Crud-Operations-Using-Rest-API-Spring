package com.hms.management;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiException extends Exception {
    private String errorMessage;

    public ApiException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;

    }

    @Override
    public String getMessage() {
        return errorMessage;
    }


}
