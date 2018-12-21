package com.harragan.battleshipsboot.controllers.exceptionhandling;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private Exception exception;
    private HttpStatus httpStatus;
    private String message;

    public ExceptionResponse(HttpStatus httpStatus, Exception ex) {
        this.exception = ex;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return exception.getMessage();
    }
}
