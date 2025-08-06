package com.mfreimueller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private Environment environment;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFound(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElement(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidState(InvalidStateException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex) {
        if (isDevelopmentProfileActive()) {
            return ex.getMessage();
        } else {
            return "An internal error occurred.";
        }
    }

    private boolean isDevelopmentProfileActive() {
        return environment.acceptsProfiles(org.springframework.core.env.Profiles.of("dev"));
    }
}
