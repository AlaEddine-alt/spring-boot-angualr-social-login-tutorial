package com.demo.social.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Exception handler for NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex,
            final WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.NOT_FOUND);
    }

    // Exception handler for InvalidException
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<Object> handleInvalidException(
            InvalidException ex,
            final WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.BAD_REQUEST);
    }

    
}