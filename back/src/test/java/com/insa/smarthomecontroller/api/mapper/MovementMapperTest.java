package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.MovementDTO;
import com.insa.smarthomecontroller.api.mapper.MovementMapperImpl;
import com.insa.smarthomecontroller.api.model.Movement;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MovementMapperTest {

    private static MovementMapper movementMapper;

    private Movement mockedMovement;
    private MovementDTO mockedMovementDTO;

    @BeforeAll
    static void initMapper() {
        movementMapper = new MovementMapperImpl();
    }

    @BeforeEach
    void init() {
        mockedMovement = Movement.builder()
                .sensorId("sensorId")
                .date(LocalDateTime.of(1998, 8, 7, 10, 20))
                .isTriggered(true)
                .build();

        mockedMovementDTO = MovementDTO.builder()
                .sensorId("sensorId")
                .date(LocalDate.of(1998, 8, 7))
                .time(LocalTime.of(10, 20))
                .isTriggered(true)
                .build();
    }

    @Test
    void testFromMovementToMovementDTO() {
        // GIVEN

        // WHEN
        MovementDTO movementDTO = movementMapper.mapMovementDTO(mockedMovement);

        // THEN
        assertAll("Grouped Assertions of MovementMapperTest",
                () -> assertEquals(mockedMovementDTO.getSensorId(), movementDTO.getSensorId()),
                () -> assertEquals(mockedMovementDTO.getDate(), movementDTO.getDate()),
                () -> assertEquals(mockedMovementDTO.getTime(), movementDTO.getTime()),
                () -> assertEquals(mockedMovementDTO.getIsTriggered(), movementDTO.getIsTriggered()));

    }

    @Test
    void testFromMovementDTOToMovement() {
        // GIVEN

        // WHEN
        Movement movement = movementMapper.mapMovement(mockedMovementDTO);

        // THEN
        assertAll("Grouped Assertions of MovementMapperTest",
                () -> assertNull(movement.getId()),
                () -> assertEquals(mockedMovement.getSensorId(), movement.getSensorId()),
                () -> assertEquals(mockedMovement.getDate(), movement.getDate()),
                () -> assertEquals(mockedMovement.getIsTriggered(), movement.getIsTriggered()));

    }

    @Test
    void testNullMapping() {
        Assertions.assertNull(movementMapper.mapMovement(null));
        Assertions.assertNull(movementMapper.mapMovementDTO(null));
    }

}