package com.example.Library.management.exception;

public class BorrowingException extends RuntimeException{

    public BorrowingException() {
    }

    public BorrowingException(String message) {
        super(message);
    }
}
