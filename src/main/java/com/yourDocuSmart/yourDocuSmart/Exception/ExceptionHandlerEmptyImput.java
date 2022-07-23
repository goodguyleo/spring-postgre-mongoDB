package com.yourDocuSmart.yourDocuSmart.Exception;


public class ExceptionHandlerEmptyImput extends RuntimeException{
    public ExceptionHandlerEmptyImput(String message) {
        super(message);
    }
    public ExceptionHandlerEmptyImput(String message, Throwable cause) {
        super(message, cause);
    }
}
