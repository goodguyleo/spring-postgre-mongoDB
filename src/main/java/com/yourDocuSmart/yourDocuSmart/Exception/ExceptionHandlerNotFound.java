package com.yourDocuSmart.yourDocuSmart.Exception;


public class ExceptionHandlerNotFound extends RuntimeException{
    public ExceptionHandlerNotFound(String message){
        super(message);
    }
    public ExceptionHandlerNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
