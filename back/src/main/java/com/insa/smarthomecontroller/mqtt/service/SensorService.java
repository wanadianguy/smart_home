package com.insa.smarthomecontroller.mqtt.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insa.smarthomecontroller.api.model.Luminosity;
import com.insa.smarthomecontroller.api.model.Movement;
import com.insa.smarthomecontroller.api.model.Temperature;
import com.insa.smarthomecontroller.api.repository.LuminosityRepository;
import com.insa.smarthomecontroller.api.repository.MovementRepository;
import com.insa.smarthomecontroller.api.repository.TemperatureRepository;
import com.insa.smarthomecontroller.mqtt.model.Sensor;
import com.insa.smarthomecontroller.mqtt.repository.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private LuminosityRepository luminosityRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Optional<Boolean> getSensorStatus(String type, String sensorId) {
        if (type.equals("Luminosity")) {
            List<Luminosity> data = luminosityRepository.findLatestBySensorId(sensorId);

            if (data.size() > 0) {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                // Test if the last data is more than 30 seconds old
                if (now.minusSeconds(30).isBefore(data.getFirst().getDate())) {
                    return Optional.of(true);
                } else {
                    return Optional.of(false);
                }
            } else {
                // If there is no data, the sensor is off
                return Optional.empty();
            }
        } else if (type.equals("Movement")) {
            List<Movement> data = movementRepository.findLatestBySensorId(sensorId);

            if (data.size() > 0) {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                // Test if the last data is more than 30 seconds old
                if (now.minusSeconds(30).isBefore(data.getFirst().getDate())) {
                    return Optional.of(true);
                } else {
                    return Optional.of(false);
                }
            } else {
                // If there is no data, the sensor is off
                return Optional.empty();
            }
        } else if (type.equals("Temperature")) {
            List<Temperature> data = temperatureRepository.findLatestBySensorId(sensorId);

            if (data.size() > 0) {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                // Test if the last data is more than 30 seconds old
                if (now.minusSeconds(30).isBefore(data.getFirst().getDate())) {
                    return Optional.of(true);
                } else {
                    return Optional.of(false);
                }
            } else {
                // If there is no data, the sensor is off
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Sensor getSensorBySensorId(String id) {
        return sensorRepository.findBySensorId(id);
    }

    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
