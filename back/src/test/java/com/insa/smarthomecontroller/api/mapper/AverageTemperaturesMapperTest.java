package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.AverageTemperaturesDTO;
import com.insa.smarthomecontroller.api.mapper.AverageTemperaturesMapperImpl;
import com.insa.smarthomecontroller.api.model.AverageTemperatures;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AverageTemperaturesMapperTest {

    private static AverageTemperaturesMapper averageTemperaturesMapper;

    private AverageTemperatures mockedAverageTemperatures;
    private AverageTemperaturesDTO mockedAverageTemperaturesDTO;

    @BeforeAll
    static void initMapper() {
        averageTemperaturesMapper = new AverageTemperaturesMapperImpl();
    }

    @BeforeEach
    void init() {
        mockedAverageTemperatures = AverageTemperatures.builder()
                .date(LocalDate.of(1998, 8, 7))
                .build();

        mockedAverageTemperaturesDTO = AverageTemperaturesDTO.builder()
                .date(LocalDate.of(1998, 8, 7))
                .build();
    }

    @Test
    void testFromAverageTemperatureToAverageTemperatureDTO() {
        // GIVEN

        // WHEN
        AverageTemperaturesDTO averageTemperaturesDTO = averageTemperaturesMapper.mapAverageTemperatureDTO(mockedAverageTemperatures);

        // THEN
        assertAll("Grouped Assertions of AverageTemperatureMapperTest",
                () -> assertEquals(mockedAverageTemperaturesDTO.getDate(), averageTemperaturesDTO.getDate()),
                () -> assertEquals(mockedAverageTemperaturesDTO.getAverageDegree(), averageTemperaturesDTO.getAverageDegree()));

    }

    @Test
    void testFromAverageTemperatureDTOToAverageTemperature() {
        // GIVEN

        // WHEN
        AverageTemperatures averageTemperatures = averageTemperaturesMapper.mapAverageTemperature(mockedAverageTemperaturesDTO);

        // THEN
        assertAll("Grouped Assertions of AverageTemperatureMapperTest",
                () -> assertNull(averageTemperatures.getId()),
                () -> assertEquals(mockedAverageTemperatures.getDate(), averageTemperatures.getDate()),
                () -> assertEquals(mockedAverageTemperatures.getAverageDegree(), averageTemperatures.getAverageDegree()));

    }

    @Test
    void testNullMapping() {
        Assertions.assertNull(averageTemperaturesMapper.mapAverageTemperature(null));
        Assertions.assertNull(averageTemperaturesMapper.mapAverageTemperatureDTO(null));
    }

}