package com.ken207.example2.exception;

import org.springframework.http.HttpStatus;

public class
BizRuntimeException extends RuntimeException {

    private String message;

    public BizRuntimeException(String message) {
        super(message);
    }
}
