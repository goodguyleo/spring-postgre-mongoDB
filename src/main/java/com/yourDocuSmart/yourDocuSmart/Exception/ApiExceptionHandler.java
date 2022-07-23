package com.yourDocuSmart.yourDocuSmart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionHandlerEmptyImput.class})
    public ResponseEntity<Object> HandleRequestExceptionEmptyInput(ExceptionHandlerEmptyImput e){
        Exceptions exceptions = new Exceptions(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionHandlerNotFound.class})
    public ResponseEntity<Object> HandleRequestExceptionNotFound(ExceptionHandlerNotFound e){
        Exceptions exceptions = new Exceptions(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(exceptions, HttpStatus.NOT_FOUND);
    }

}
