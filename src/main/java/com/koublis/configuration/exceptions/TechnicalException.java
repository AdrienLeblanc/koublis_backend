package com.koublis.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends RuntimeException {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Exception ex) {
        super(message, ex);
    }

    public TechnicalException(Reason reason) {
        super(reason.getMessage());
    }

    public TechnicalException(Reason reason, Exception ex) {
        super(reason.getMessage(), ex);
    }
}
