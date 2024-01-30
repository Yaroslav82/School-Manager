package com.hillel.multi.configuration.exceptions.handler;

import com.hillel.multi.configuration.exceptions.ErrorDetails;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.configuration.exceptions.UserExistsException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({JwtException.class, AccessDeniedException.class})
    public final ResponseEntity<ErrorDetails> handleJwtException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorDetails errorDetails = new ErrorDetails("Wrong username or password", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorDetails> handleValidationException(ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        ex.getConstraintViolations().forEach(v ->
                builder.append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n ")
        );
        ErrorDetails errorDetails = new ErrorDetails(builder.toString(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler({NotFoundException.class, MediaTypeException.class, UserExistsException.class})
    public final ResponseEntity<ErrorDetails> handleCustomExceptions(Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        HttpStatus status = responseStatus != null ? responseStatus.code() : HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), status.value());
        return new ResponseEntity<>(errorDetails, status);
    }
}
