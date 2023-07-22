package com.example.backend.support;

public class UtenteNotExistingException extends RuntimeException{

    private String message;

    public UtenteNotExistingException(String message) {
        super(message);
    }

}
