package com.gabrielsantos.app.utils;

import org.springframework.beans.MethodInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TrataExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> erros = e.getBindingResult()
                .getAllErrors();
        List<String> errosString = new ArrayList<>();
        erros.forEach((err) -> {
            errosString.add(err.getDefaultMessage());
        });
        return new ResponseEntity(errosString, HttpStatus.BAD_REQUEST);
    }
}
