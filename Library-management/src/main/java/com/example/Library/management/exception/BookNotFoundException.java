package com.example.Library.management.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
