package com.insa.smarthomecontroller.api.service;

import com.insa.smarthomecontroller.api.mapper.TemperatureMapper;
import com.insa.smarthomecontroller.api.dto.AverageTemperaturesDTO;
import com.insa.smarthomecontroller.api.dto.TemperatureDTO;
import com.insa.smarthomecontroller.api.exception.TemperatureException;
import com.insa.smarthomecontroller.api.mapper.TemperatureMapperImpl;
import com.insa.smarthomecontroller.api.model.AverageTemperatures;
import com.insa.smarthomecontroller.api.model.Temperature;
import com.insa.smarthomecontroller.api.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TemperatureService {

    private final TemperatureMapper temperatureMapper;
    private final TemperatureRepository temperatureRepository;

    private final AverageTemperaturesService averageTemperaturesService;

    public TemperatureService(TemperatureRepository temperatureRepository, AverageTemperaturesService averageTemperaturesService) {
        this.temperatureRepository = temperatureRepository;
        this.temperatureMapper = new TemperatureMapperImpl();

        this.averageTemperaturesService = averageTemperaturesService;
    }

    public TemperatureDTO getCurrentTemperature(String sensorId) throws TemperatureException {
        Temperature latestTemperature = temperatureRepository.findFirstBySensorIdOrderByDateDesc(sensorId);

        if (Objects.isNull(latestTemperature)) {
            throw new TemperatureException("No temperature found.");
        }

        return temperatureMapper.mapTemperatureDTO(latestTemperature);
    }

    public List<TemperatureDTO> getAllTemperaturesOfToday(String sensorId) throws TemperatureException {
        LocalDate today = LocalDate.now();
        List<Temperature> temperatures = temperatureRepository.findAllBySensorIdAndDateBetween(sensorId, today, today.plusDays(1));

        if (temperatures.isEmpty()) {
            throw new TemperatureException("No temperature found for today.");
        }

        return temperatures.stream().map(temperatureMapper::mapTemperatureDTO).sorted(Comparator.comparing(TemperatureDTO::getDate).thenComparing(TemperatureDTO::getTime)).toList();
    }

    private List<Temperature> getAllTemperaturesOfASpecificDay(String sensorId, LocalDate day) {
        return temperatureRepository.findAllBySensorIdAndDateBetween(sensorId, day, day.plusDays(1));
    }

    private Double calculateAverageDegrees(List<Temperature> temperatures) {
        return temperatures.stream().mapToDouble(Temperature::getDegree).average().orElse(0.0);
    }

    public TemperatureDTO createTemperature(TemperatureDTO temperatureDTO) throws TemperatureException {
        Temperature temperature = temperatureMapper.mapTemperature(temperatureDTO);
        Temperature savedTemperature = temperatureRepository.save(temperature);

        AverageTemperatures averageTemperatures = this.averageTemperaturesService.getAverageTemperaturesByDate(temperatureDTO.getSensorId(), temperatureDTO.getDate());

        if (Objects.nonNull(averageTemperatures)) {
            List<Temperature> temperatures = this.getAllTemperaturesOfASpecificDay(temperatureDTO.getSensorId(), temperatureDTO.getDate());
            this.averageTemperaturesService.updateAverageTemperature(averageTemperatures.getId(), this.calculateAverageDegrees(temperatures));
        } else {
            this.averageTemperaturesService.createAverageTemperature(new AverageTemperaturesDTO(temperature.getSensorId(), temperatureDTO.getDate(), temperatureDTO.getDegree()));
        }

        return temperatureMapper.mapTemperatureDTO(savedTemperature);
    }

    public TemperatureDTO deleteTemperature(String id) throws TemperatureException {
        Optional<Temperature> optionalTemperature = temperatureRepository.findById(id);

        if (optionalTemperature.isEmpty()) {
            throw new TemperatureException("Temperature not found with id: " + id);
        }

        Temperature temperatureToDelete = optionalTemperature.get();
        temperatureRepository.delete(temperatureToDelete);

        List<Temperature> temperatures = this.getAllTemperaturesOfASpecificDay(temperatureToDelete.getSensorId(), temperatureToDelete.getDate().toLocalDate());
        AverageTemperatures averageTemperatures = this.averageTemperaturesService.getAverageTemperaturesByDate(temperatureToDelete.getSensorId(), temperatureToDelete.getDate().toLocalDate());

        if (temperatures.isEmpty()) {
            this.averageTemperaturesService.deleteAverageTemperature(averageTemperatures.getId());
        } else {
            this.averageTemperaturesService.updateAverageTemperature(averageTemperatures.getId(), this.calculateAverageDegrees(temperatures));
        }

        return temperatureMapper.mapTemperatureDTO(temperatureToDelete);
    }
}
