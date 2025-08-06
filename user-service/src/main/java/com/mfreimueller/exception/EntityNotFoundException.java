package com.mfreimueller.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("Could not find entity " + id);
    }

    public EntityNotFoundException(String id, String entity) {
        super("Could not find " + entity + " " + id);
    }

}
