package com.hillel.multi.configuration.exceptions;

public class NotFoundException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "The specified resource was not found.";

    public NotFoundException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
