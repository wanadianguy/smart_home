package com.insa.smarthomecontroller.mqtt.model;

public class MovementMQTT {
    private Integer timestamp;
    private Boolean obstacle;
    private String id;

    public Integer getTimestamp() {
        return timestamp;
    }

    public Boolean getObstacle() {
        return obstacle;
    }

    public String getId() {
        return id;
    }
}
