package com.hillel.multi.configuration.exceptions;

public class MediaTypeException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "Unsupported Media Type.";

    public MediaTypeException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public MediaTypeException(String message) {
        super(message);
    }
}
