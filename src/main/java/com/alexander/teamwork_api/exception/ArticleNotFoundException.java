package com.alexander.teamwork_api.exception;

// Thrown when an article cannot be found.
public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }

}