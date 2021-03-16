package com.example.bankapp.exceptions;

public class InvalidIbanException extends RuntimeException {
    public InvalidIbanException() {
        super();
    }

    public InvalidIbanException(String message) {
        super(message);
    }
}
