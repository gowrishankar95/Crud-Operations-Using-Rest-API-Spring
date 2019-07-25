package com.hms.management.rest;

import com.hms.management.UserManagerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserManagementRestControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = UserManagerException.class)
    public Map<String, String> invalidUserNameException() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("error", "invalid user name or password");

        return map;

    }




}

