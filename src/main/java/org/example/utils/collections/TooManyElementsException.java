package org.example.utils.collections;

public class TooManyElementsException extends RuntimeException{
    public TooManyElementsException() {
    }

    public TooManyElementsException(String message) {
        super(message);
    }
}
