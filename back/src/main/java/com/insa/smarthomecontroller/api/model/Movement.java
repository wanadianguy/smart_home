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
@Document(collection = "movements")
public class Movement {

    @Id
    private String id;
    @Field("sensorId")
    private String sensorId;
    @Field("dateTime")
    private LocalDateTime date;
    @Field("isTriggered")
    private Boolean isTriggered;
}
