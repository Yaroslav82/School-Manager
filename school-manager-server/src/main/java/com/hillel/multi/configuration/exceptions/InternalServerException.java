package com.hillel.multi.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "The server encountered an unexpected error while processing the request.";

    public InternalServerException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
