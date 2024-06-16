package com.insa.smarthomecontroller.api.exception;

public class AverageTemperaturesException extends RuntimeException {

    public AverageTemperaturesException(String message) {
        super(message);
    }

    public AverageTemperaturesException(String message, Throwable cause) {
        super(message, cause);
    }
}
