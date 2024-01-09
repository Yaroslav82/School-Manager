package com.hillel.multi.configuration.handler;

import com.hillel.multi.configuration.exceptions.ErrorDetails;
import com.hillel.multi.configuration.exceptions.InternalServerException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),  HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MediaTypeException.class)
    public final ResponseEntity<ErrorDetails> handleMediaTypeException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),  HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(InternalServerException.class)
    public final ResponseEntity<ErrorDetails> handleInternalServerException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
