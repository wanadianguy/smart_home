package com.insa.smarthomecontroller.api.controller;

import com.insa.smarthomecontroller.api.exception.AverageTemperaturesException;
import com.insa.smarthomecontroller.api.exception.LuminosityException;
import com.insa.smarthomecontroller.api.exception.MovementException;
import com.insa.smarthomecontroller.api.exception.TemperatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AverageTemperaturesException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String averageTemperaturesNotFoundHandler(AverageTemperaturesException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LuminosityException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String lumonisotyNotFoundHandler(LuminosityException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MovementException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String movementNotFoundHandler(MovementException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TemperatureException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String temperatureNotFoundHandler(TemperatureException ex) {
        return ex.getMessage();
    }
}