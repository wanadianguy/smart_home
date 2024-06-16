package com.insa.smarthomecontroller.mqtt.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensors")
public class Sensor {

    @Id
    private String id;
    @Field("sensorId")
    private String sensorId;
    @Field("type")
    private String type;
    @Field("description")
    private String description;
}
