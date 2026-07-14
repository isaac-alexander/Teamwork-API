package com.alexander.teamwork_api.exception;

// Thrown when login credentials are incorrect.
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }

}