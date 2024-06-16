package com.insa.smarthomecontroller.mqtt.model;

public class TemperatureMQTT {
    private Integer timestamp;
    private Double degree;
    private String id;

    public Integer getTimestamp() {
        return timestamp;
    }

    public Double getDegree() {
        return degree;
    }

    public String getId() {
        return id;
    }
}
