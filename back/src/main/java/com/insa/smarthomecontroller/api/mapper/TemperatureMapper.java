package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.TemperatureDTO;
import com.insa.smarthomecontroller.api.model.Temperature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper
public interface TemperatureMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "getLocalDateFromLocalDateTime")
    @Mapping(source = "date", target = "time", qualifiedByName = "getLocalTimeFromLocalDateTime")
    TemperatureDTO mapTemperatureDTO(Temperature temperature);

    @Mapping(source = ".", target = "date", qualifiedByName = "mapLocalDateAndTimeToDateTime")
    @Mapping(target = "id", ignore = true)
    Temperature mapTemperature(TemperatureDTO temperatureDTO);

    @Named("mapLocalDateAndTimeToDateTime")
    default LocalDateTime mapLocalDateAndTimeToDateTime(TemperatureDTO temperatureDTO) {
        return LocalDateTime.of(temperatureDTO.getDate(), temperatureDTO.getTime());
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
