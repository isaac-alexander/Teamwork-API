package com.alexander.teamwork_api.exception;

// Thrown when a comment cannot be found.
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }

}