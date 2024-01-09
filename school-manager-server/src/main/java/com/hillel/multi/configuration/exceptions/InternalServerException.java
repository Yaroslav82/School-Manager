package com.hillel.multi.configuration.exceptions;

public class InternalServerException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "The server encountered an unexpected error while processing the request.";

    public InternalServerException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
