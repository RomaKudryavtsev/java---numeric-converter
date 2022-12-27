package org.example.NumericConverter.exception;

public class InvalidBaseExeption extends RuntimeException {
    public InvalidBaseExeption() {
    }

    public InvalidBaseExeption(final String message) {
        super(message);
    }
}
