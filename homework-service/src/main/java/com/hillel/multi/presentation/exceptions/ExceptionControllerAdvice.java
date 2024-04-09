package com.hillel.multi.presentation.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorDetails> handleValidationException(ConstraintViolationException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage())
        );
        ErrorDetails details = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.value(), errors);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
