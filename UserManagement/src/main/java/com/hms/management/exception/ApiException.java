package com.hms.management.exception;

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
