package com.insa.smarthomecontroller.mqtt.model;

import java.util.Map;

public class SpectralMQTT {
    private Integer timestamp;
    private String id;
    private Map<String, Integer> data;

    public Integer getTimestamp() {
        return timestamp;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public Double getLuminosity() {
        Integer sum = 0;
        Integer nbOndes = 0;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            if (entry.getKey().contains("nm")) {
                sum += entry.getValue();
                nbOndes++;
            }
        }

        Integer moyenne = sum / nbOndes;

        Double luminosity = moyenne / 65535.0;

        return luminosity;
    }
}
