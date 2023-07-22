package com.example.backend.support;

public class UtenteAlreadyExistingException extends RuntimeException {

    private String message;

    public UtenteAlreadyExistingException(String message) {
        super(message);
    }
}
