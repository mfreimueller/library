package com.mfreimueller.exception;

public class InvalidStateException extends RuntimeException {

    public InvalidStateException(Long id) {
        super("Invalid state for entity %d".formatted(id));
    }

    public InvalidStateException(Long id, String description) {
        super("Invalid state for entity %d: %s".formatted(id, description));
    }

}
