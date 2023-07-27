package com.works.team.configs;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,Object> hm = new LinkedHashMap<>();

        List<Object> errors = new ArrayList<>();

        for (int i = 0; i < ex.getFieldErrors().size(); i++) {
            Map<String,Object> hm2 = new LinkedHashMap<>();
            hm2.put("field",ex.getFieldErrors().get(i).getField());
            hm2.put("defaultMessage",ex.getFieldErrors().get(i).getDefaultMessage());
            hm2.put("code",ex.getFieldErrors().get(i).getCode());
            errors.add(hm2);
        }

        hm.put("status",false);
        hm.put("result",errors);
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolation(){
        String message = "This player is already exist !";
        Map<String,String> hm = new LinkedHashMap<>();
        hm.put("error",message);
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }
}
