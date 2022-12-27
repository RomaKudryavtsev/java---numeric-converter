package org.example.NumericConverter.exception;

public class InvalidInputNumberException extends RuntimeException {
    public InvalidInputNumberException() {
    }

    public InvalidInputNumberException(final String message) {
        super(message);
    }
}
