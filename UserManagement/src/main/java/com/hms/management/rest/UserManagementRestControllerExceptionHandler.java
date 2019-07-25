package com.hms.management.rest;

import com.hms.management.rest.Exceptions.InvalidNameException;
import com.hms.management.rest.Exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserManagementRestControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = InvalidNameException.class)
    public Map<String, String> invalidUserNameException() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("error", "invalid user name. please enter a valid user name");

        return map;

    }

    @ResponseBody
    @ExceptionHandler(value = InvalidPasswordException.class)
    public Map<String, String> invalidUserPasswordException() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("error", "invalid password. please enter a valid password less than 20 characters");

        return map;

    }


}

