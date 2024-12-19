package com.employee.singleapidemo.exception;

import org.springframework.http.HttpStatus;

public class EmployeeAlreadyExistException extends RuntimeException {
    String message;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public EmployeeAlreadyExistException(String message) {
        super();
        this.message = message;
    }
}
