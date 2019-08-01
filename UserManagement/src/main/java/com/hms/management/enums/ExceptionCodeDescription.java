package com.hms.management.enums;

public enum ExceptionCodeDescription {
    INVALID_USERNAME(0,"invalid user name"),
    INVALID_PASSWORD(1,"invalid password");

    private final int code;
    private final String description;

    ExceptionCodeDescription(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
