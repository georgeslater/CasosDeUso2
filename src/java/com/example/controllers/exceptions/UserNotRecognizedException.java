package com.example.controllers.exceptions;

public class UserNotRecognizedException extends Exception {
    public UserNotRecognizedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotRecognizedException(String message) {
        super(message);
    }
}