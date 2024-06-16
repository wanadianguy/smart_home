package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.TemperatureDTO;
import com.insa.smarthomecontroller.api.mapper.TemperatureMapperImpl;
import com.insa.smarthomecontroller.api.model.Temperature;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureMapperTest {

    private static TemperatureMapper temperatureMapper;

    private Temperature mockedTemperature;
    private TemperatureDTO mockedTemperatureDTO;

    @BeforeAll
    static void initMapper() {
        temperatureMapper = new TemperatureMapperImpl();
    }

    @BeforeEach
    void init() {
        mockedTemperature = Temperature.builder()
                .sensorId("sensorId")
                .date(LocalDateTime.of(1998, 8, 7, 10, 20))
                .degree(23.0)
                .build();

        mockedTemperatureDTO = TemperatureDTO.builder()
                .sensorId("sensorId")
                .date(LocalDate.of(1998, 8, 7))
                .time(LocalTime.of(10, 20))
                .degree(23.0)
                .build();
    }

    @Test
    void testFromTemperatureToTemperatureDTO() {
        // GIVEN

        // WHEN
        TemperatureDTO temperatureDTO = temperatureMapper.mapTemperatureDTO(mockedTemperature);

        // THEN
        assertAll("Grouped Assertions of TemperatureMapperTest",
                () -> assertEquals(mockedTemperatureDTO.getSensorId(), temperatureDTO.getSensorId()),
                () -> assertEquals(mockedTemperatureDTO.getDate(), temperatureDTO.getDate()),
                () -> assertEquals(mockedTemperatureDTO.getTime(), temperatureDTO.getTime()),
                () -> assertEquals(mockedTemperatureDTO.getDegree(), temperatureDTO.getDegree()));

    }

    @Test
    void testFromTemperatureDTOToTemperature() {
        // GIVEN

        // WHEN
        Temperature temperature = temperatureMapper.mapTemperature(mockedTemperatureDTO);

        // THEN
        assertAll("Grouped Assertions of TemperatureMapperTest",
                () -> assertNull(temperature.getId()),
                () -> assertEquals(mockedTemperature.getSensorId(), temperature.getSensorId()),
                () -> assertEquals(mockedTemperature.getDate(), temperature.getDate()),
                () -> assertEquals(mockedTemperature.getDegree(), temperature.getDegree()));

    }

    @Test
    void testNullMapping() {
        Assertions.assertNull(temperatureMapper.mapTemperature(null));
        Assertions.assertNull(temperatureMapper.mapTemperatureDTO(null));
    }

}