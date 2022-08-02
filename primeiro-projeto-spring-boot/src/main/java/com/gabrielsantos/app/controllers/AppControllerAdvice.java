package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.utils.TesteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(TesteException.class)
    public ResponseEntity trataTesteException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
