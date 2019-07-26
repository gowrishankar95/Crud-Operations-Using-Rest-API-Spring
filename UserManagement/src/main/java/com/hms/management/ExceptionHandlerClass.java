package com.hms.management;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerClass {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> exception(Exception e) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("error", e.getMessage());
        return map;

    }




}

