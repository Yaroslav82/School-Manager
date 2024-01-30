package com.hillel.multi.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistsException extends RuntimeException {

    private final static String DEFAULT_RESPONSE_MESSAGE = "A user with the same username already exists.";

    public UserExistsException() {
        super(DEFAULT_RESPONSE_MESSAGE);
    }

    public UserExistsException(String message) {
        super(message);
    }
}
