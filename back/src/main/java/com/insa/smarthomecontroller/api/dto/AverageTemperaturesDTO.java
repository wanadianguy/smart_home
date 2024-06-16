package com.insa.smarthomecontroller.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AverageTemperaturesDTO {

    @NotNull
    private String sensorId;
    @NotNull
    private LocalDate date;
    @NotNull
    private Double averageDegree;
}
