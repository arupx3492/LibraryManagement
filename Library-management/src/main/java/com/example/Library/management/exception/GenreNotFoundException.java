package com.example.Library.management.exception;

public class GenreNotFoundException extends RuntimeException{

    public GenreNotFoundException() {
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
