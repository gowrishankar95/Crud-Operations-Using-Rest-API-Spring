package com.hms.management;

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
