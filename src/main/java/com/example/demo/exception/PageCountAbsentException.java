package com.example.demo.exception;


public class PageCountAbsentException extends RuntimeException {

    public PageCountAbsentException(String message) {
        super(message);
    }
}
