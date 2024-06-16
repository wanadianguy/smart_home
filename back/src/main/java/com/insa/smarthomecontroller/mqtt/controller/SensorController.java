package com.insa.smarthomecontroller.mqtt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insa.smarthomecontroller.mqtt.dto.SensorDTO;
import com.insa.smarthomecontroller.mqtt.model.Sensor;
import com.insa.smarthomecontroller.mqtt.service.SensorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sensors")
@CrossOrigin
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/all")
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();

        List<SensorDTO> sensorsDTO = new ArrayList<>();

        for (Sensor sensor : sensors) {
            Optional<Boolean> state = sensorService.getSensorStatus(sensor.getType(), sensor.getSensorId());

            if (state.isPresent()) {
                // The sensor is present inside the sensor collection and have data registered
                sensorsDTO.add(
                        new SensorDTO(sensor.getSensorId(), sensor.getType(), state.get(), sensor.getDescription()));
            }
        }

        return new ResponseEntity<>(sensorsDTO, HttpStatus.valueOf(200));
    }

    @PatchMapping("/description/{id}")
    public ResponseEntity<Sensor> updateDescription(@PathVariable String id,
            @Valid @RequestBody String description) {
        Sensor sensor = sensorService.getSensorBySensorId(id);

        if (sensor == null) {
            return new ResponseEntity<>(sensor, HttpStatus.valueOf(404));
        }

        sensor.setDescription(description);
        sensorService.saveSensor(sensor);

        return new ResponseEntity<>(sensor, HttpStatus.valueOf(200));
    }

    @GetMapping("/status/{type}/{id}")
    public ResponseEntity<Boolean> getStatusSensor(@PathVariable String type, @PathVariable String id) {
        Optional<Boolean> res = sensorService.getSensorStatus(type, id);

        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(false, HttpStatus.valueOf(404));
        }
    }
}
