package com.insa.smarthomecontroller.mqtt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {
    @NotNull
    private String sensorId;
    @NotNull
    private String type;
    @NotNull
    private Boolean state;
    @NotNull
    private String description;
}
