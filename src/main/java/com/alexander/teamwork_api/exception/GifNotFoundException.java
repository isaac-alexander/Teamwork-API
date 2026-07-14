package com.alexander.teamwork_api.exception;

// Thrown when a GIF cannot be found.
public class GifNotFoundException extends RuntimeException {

    public GifNotFoundException(String message) {
        super(message);
    }

}