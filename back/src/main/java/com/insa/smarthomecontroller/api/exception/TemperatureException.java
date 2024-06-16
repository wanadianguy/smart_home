package com.insa.smarthomecontroller.api.exception;

public class TemperatureException extends RuntimeException {

    public TemperatureException(String message) {
        super(message);
    }

    public TemperatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
