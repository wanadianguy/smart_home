package com.insa.smarthomecontroller.api.exception;

public class MovementException extends RuntimeException {

    public MovementException(String message) {
        super(message);
    }

    public MovementException(String message, Throwable cause) {
        super(message, cause);
    }
}
