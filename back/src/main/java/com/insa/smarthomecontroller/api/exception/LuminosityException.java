package com.insa.smarthomecontroller.api.exception;

public class LuminosityException extends RuntimeException {

    public LuminosityException(String message) {
        super(message);
    }

    public LuminosityException(String message, Throwable cause) {
        super(message, cause);
    }
}
