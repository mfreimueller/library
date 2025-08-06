package com.mfreimueller.exception;

public class InvalidStateException extends RuntimeException {

    public InvalidStateException(String id) {
        super("Invalid state for entity %s".formatted(id));
    }

    public InvalidStateException(String id, String description) {
        super("Invalid state for entity %s: %s".formatted(id, description));
    }

}
