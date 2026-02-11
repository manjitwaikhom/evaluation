package com.example.demo.exception;


public class TitleAbsentException extends RuntimeException {

    public TitleAbsentException(String message) {
        super(message);
    }
}
