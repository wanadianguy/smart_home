package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.LuminosityDTO;
import com.insa.smarthomecontroller.api.model.Luminosity;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LuminosityMapperTest {

    private static LuminosityMapper luminosityMapper;

    private Luminosity mockedLuminosity;
    private LuminosityDTO mockedLuminosityDTO;

    @BeforeAll
    static void initMapper() {
        luminosityMapper = new LuminosityMapperImpl();
    }

    @BeforeEach
    void init() {
        mockedLuminosity = Luminosity.builder()
                .sensorId("sensorId")
                .date(LocalDateTime.of(1998, 8, 7, 10, 20))
                .percentOfLuminosity(0.23)
                .build();

        mockedLuminosityDTO = LuminosityDTO.builder()
                .sensorId("sensorId")
                .date(LocalDate.of(1998, 8, 7))
                .time(LocalTime.of(10, 20))
                .percentOfLuminosity(0.23)
                .build();
    }

    @Test
    void testFromTemperatureToTemperatureDTO() {
        // GIVEN

        // WHEN
        LuminosityDTO luminosityDTO = luminosityMapper.mapLuminosityDTO(mockedLuminosity);

        // THEN
        assertAll("Grouped Assertions of LuminosityMapperTest",
                () -> assertEquals(mockedLuminosityDTO.getSensorId(), luminosityDTO.getSensorId()),
                () -> assertEquals(mockedLuminosityDTO.getDate(), luminosityDTO.getDate()),
                () -> assertEquals(mockedLuminosityDTO.getTime(), luminosityDTO.getTime()),
                () -> assertEquals(mockedLuminosityDTO.getPercentOfLuminosity(), luminosityDTO.getPercentOfLuminosity()));

    }

    @Test
    void testFromTemperatureDTOToTemperature() {
        // GIVEN

        // WHEN
        Luminosity luminosity = luminosityMapper.mapLuminosity(mockedLuminosityDTO);

        // THEN
        assertAll("Grouped Assertions of TemperatureMapperTest",
                () -> assertNull(luminosity.getId()),
                () -> assertEquals(mockedLuminosity.getSensorId(), luminosity.getSensorId()),
                () -> assertEquals(mockedLuminosity.getDate(), luminosity.getDate()),
                () -> assertEquals(mockedLuminosity.getPercentOfLuminosity(), luminosity.getPercentOfLuminosity()));

    }

    @Test
    void testNullMapping() {
        Assertions.assertNull(luminosityMapper.mapLuminosity(null));
        Assertions.assertNull(luminosityMapper.mapLuminosityDTO(null));
    }

}