package com.insa.smarthomecontroller.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "temperatures")
public class Temperature {

    @Id
    private String id;
    @Field("sensorId")
    private String sensorId;
    @Field("dateTime")
    private LocalDateTime date;
    @Field("degree")
    private Double degree;
}
