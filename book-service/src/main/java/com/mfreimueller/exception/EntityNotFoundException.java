package com.mfreimueller.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Could not find entity " + id);
    }

    public EntityNotFoundException(Long id, String entity) {
        super("Could not find " + entity + " " + id);
    }

}
