package com.groades.nttdata.common;

public class NonExistingUserException extends RuntimeException {
    public NonExistingUserException(String message) {
        super(message);
    }
}
