package com.insa.smarthomecontroller.api.mapper;

import com.insa.smarthomecontroller.api.dto.AverageTemperaturesDTO;
import com.insa.smarthomecontroller.api.model.AverageTemperatures;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AverageTemperaturesMapper {

    AverageTemperaturesDTO mapAverageTemperatureDTO(AverageTemperatures averageTemperatures);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    AverageTemperatures mapAverageTemperature(AverageTemperaturesDTO averageTemperaturesDTO);
}
