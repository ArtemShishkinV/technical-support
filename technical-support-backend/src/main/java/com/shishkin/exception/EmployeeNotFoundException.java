package com.shishkin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeNotFoundException extends ResponseStatusException {
    public EmployeeNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
