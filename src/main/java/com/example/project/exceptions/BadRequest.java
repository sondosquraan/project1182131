package com.example.project.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest(String message, String id) {
        super(message);
    }

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}
