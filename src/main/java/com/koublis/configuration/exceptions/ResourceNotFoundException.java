package com.koublis.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception ex) {
        super(message, ex);
    }

    public ResourceNotFoundException(Reason reason) {
        super(reason.getMessage());
    }

    public ResourceNotFoundException(Reason reason, Exception ex) {
        super(reason.getMessage(), ex);
    }
}