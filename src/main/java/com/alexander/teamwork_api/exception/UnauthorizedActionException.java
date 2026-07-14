package com.alexander.teamwork_api.exception;

// Thrown when a user tries to perform an action they are not allowed to perform.
public class UnauthorizedActionException extends RuntimeException {

    public UnauthorizedActionException(String message) {
        super(message);
    }

}