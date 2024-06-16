package com.insa.smarthomecontroller.api.service;

import com.insa.smarthomecontroller.api.mapper.AverageTemperaturesMapper;
import com.insa.smarthomecontroller.api.dto.AverageTemperaturesDTO;
import com.insa.smarthomecontroller.api.exception.AverageTemperaturesException;
import com.insa.smarthomecontroller.api.mapper.AverageTemperaturesMapperImpl;
import com.insa.smarthomecontroller.api.model.AverageTemperatures;
import com.insa.smarthomecontroller.api.repository.AverageTemperaturesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AverageTemperaturesService {

    private final AverageTemperaturesMapper averageTemperaturesMapper;
    private final AverageTemperaturesRepository averageTemperaturesRepository;

    public AverageTemperaturesService(AverageTemperaturesRepository averageTemperaturesRepository) {
        this.averageTemperaturesRepository = averageTemperaturesRepository;
        this.averageTemperaturesMapper = new AverageTemperaturesMapperImpl();
    }

    public List<AverageTemperaturesDTO> getAllAverageTemperaturesOnSetDuration(String sensorId, LocalDate startDate, LocalDate endDate) {
        return averageTemperaturesRepository.findAllBySensorIdAndDateBetween(sensorId, startDate, endDate.plusDays(1))
                .stream()
                .map(averageTemperaturesMapper::mapAverageTemperatureDTO)
                .sorted(Comparator.comparing(AverageTemperaturesDTO::getDate))
                .toList();
    }

    public AverageTemperatures getAverageTemperaturesByDate(String sensorId, LocalDate date) throws AverageTemperaturesException {
        return averageTemperaturesRepository.findBySensorIdAndDate(sensorId, date);
    }

    public AverageTemperaturesDTO createAverageTemperature(AverageTemperaturesDTO averageTemperaturesDTO) {
        AverageTemperatures averageTemperatures = averageTemperaturesMapper
                .mapAverageTemperature(averageTemperaturesDTO);
        AverageTemperatures savedEntity = averageTemperaturesRepository.save(averageTemperatures);

        return averageTemperaturesMapper.mapAverageTemperatureDTO(savedEntity);
    }

    public AverageTemperaturesDTO updateAverageTemperature(String id, double newAverageDegree)
            throws AverageTemperaturesException {
        Optional<AverageTemperatures> optionalAverageTemperatures = averageTemperaturesRepository.findById(id);

        if (optionalAverageTemperatures.isPresent()) {
            AverageTemperatures averageTemperaturesToUpdate = optionalAverageTemperatures.get();
            averageTemperaturesToUpdate.setAverageDegree(newAverageDegree);
            averageTemperaturesRepository.save(averageTemperaturesToUpdate);

            return averageTemperaturesMapper.mapAverageTemperatureDTO(averageTemperaturesToUpdate);
        } else {
            throw new AverageTemperaturesException("Average temperature not found with id: " + id);
        }
    }

    public AverageTemperaturesDTO deleteAverageTemperature(String id) throws AverageTemperaturesException {
        Optional<AverageTemperatures> optionalAverageTemperatures = averageTemperaturesRepository.findById(id);

        if (optionalAverageTemperatures.isPresent()) {
            AverageTemperatures averageTemperaturesToDelete = optionalAverageTemperatures.get();
            averageTemperaturesRepository.delete(averageTemperaturesToDelete);

            return averageTemperaturesMapper.mapAverageTemperatureDTO(averageTemperaturesToDelete);
        } else {
            throw new AverageTemperaturesException("Average temperature not found with id: " + id);
        }
    }
}
