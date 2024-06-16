package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.MovementDTO;
import com.insa.smarthomecontroller.api.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper
public interface MovementMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "getLocalDateFromLocalDateTime")
    @Mapping(source = "date", target = "time", qualifiedByName = "getLocalTimeFromLocalDateTime")
    MovementDTO mapMovementDTO(Movement movement);

    @Mapping(source = ".", target = "date", qualifiedByName = "mapLocalDateAndTimeToDateTime")
    @Mapping(target = "id", ignore = true)
    Movement mapMovement(MovementDTO movementDTO);

    @Named("mapLocalDateAndTimeToDateTime")
    default LocalDateTime mapLocalDateAndTimeToDateTime(MovementDTO movementDTO) {
        return LocalDateTime.of(movementDTO.getDate(), movementDTO.getTime());
    }

    @Named("getLocalDateFromLocalDateTime")
    default LocalDate getLocalDateFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    @Named("getLocalTimeFromLocalDateTime")
    default LocalTime getLocalTimeFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
