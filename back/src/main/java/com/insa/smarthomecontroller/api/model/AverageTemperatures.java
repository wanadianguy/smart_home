package com.insa.smarthomecontroller.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "averages-temperatures")
public class AverageTemperatures {

    @Id
    private String id;
    @Field("sensorId")
    private String sensorId;
    @Field("date")
    private LocalDate date;
    @Field("averageDegree")
    private Double averageDegree;
}
