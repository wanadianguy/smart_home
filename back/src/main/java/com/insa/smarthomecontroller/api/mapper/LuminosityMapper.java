package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.LuminosityDTO;
import com.insa.smarthomecontroller.api.model.Luminosity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface LuminosityMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "getLocalDateFromLocalDateTime")
    @Mapping(source = "date", target = "time", qualifiedByName = "getLocalTimeFromLocalDateTime")
    LuminosityDTO mapLuminosityDTO(Luminosity luminosity);

    @Mapping(source = ".", target = "date", qualifiedByName = "mapLocalDateAndTimeToDateTime")
    @Mapping(target = "id", ignore = true)
    Luminosity mapLuminosity(LuminosityDTO luminosityDTO);

    @Named("mapLocalDateAndTimeToDateTime")
    default LocalDateTime mapLocalDateAndTimeToDateTime(LuminosityDTO luminosityDTO) {
        return LocalDateTime.of(luminosityDTO.getDate(), luminosityDTO.getTime());
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
