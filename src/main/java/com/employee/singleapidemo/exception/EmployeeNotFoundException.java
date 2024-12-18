package com.employee.singleapidemo.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends RuntimeException {

    private HttpStatus httpStatusCode;
    private String message;

    public EmployeeNotFoundException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

}
