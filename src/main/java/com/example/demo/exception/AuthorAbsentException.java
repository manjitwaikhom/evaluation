package com.example.demo.exception;


public class AuthorAbsentException extends RuntimeException {

    public AuthorAbsentException(String message) {
        super(message);
    }
}
