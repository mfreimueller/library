package com.mfreimueller.exception;

import com.mfreimueller.dto.ExceptionDto;
import com.mfreimueller.dto.ViolatedConstraintsDto;
import com.mfreimueller.dto.ViolatedFieldDto;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(InvalidStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidState(InvalidStateException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ViolatedConstraintsDto handleConstraintViolation(ConstraintViolationException ex) {
        List<ViolatedFieldDto> violations = ex.getConstraintViolations().stream()
                .map(cv -> new ViolatedFieldDto(cv.getPropertyPath().toString(), cv.getMessage()))
                .toList();

        return new ViolatedConstraintsDto("Unable to perform operations due to violated constraints", violations);
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleTransactionSystemException(TransactionSystemException ex) {
        ConstraintViolationException constraintViolationException = extractConstraintViolationException(ex);
        if (constraintViolationException != null) {
            return handleConstraintViolation(constraintViolationException);
        }

        return new ExceptionDto(ex.getMessage());
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

    private ConstraintViolationException extractConstraintViolationException(Throwable throwable) {
        while (throwable != null) {
            if (throwable instanceof ConstraintViolationException) {
                return (ConstraintViolationException) throwable;
            }

            throwable = throwable.getCause();
        }

        return null;
    }
}
