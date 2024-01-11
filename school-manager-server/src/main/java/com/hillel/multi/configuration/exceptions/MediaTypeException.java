package com.hillel.multi.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class MediaTypeException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "Unsupported Media Type.";

    public MediaTypeException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public MediaTypeException(String message) {
        super(message);
    }
}
