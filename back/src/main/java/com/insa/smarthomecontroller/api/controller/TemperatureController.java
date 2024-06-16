package com.insa.smarthomecontroller.api.controller;

import com.insa.smarthomecontroller.api.dto.TemperatureDTO;
import com.insa.smarthomecontroller.api.service.TemperatureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temperatures")
@CrossOrigin
public class TemperatureController {

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/current/{sensorId}")
    public ResponseEntity<TemperatureDTO> getCurrentTemperature(@PathVariable String sensorId) {
        return ResponseEntity.ok(this.temperatureService.getCurrentTemperature(sensorId));
    }

    @GetMapping("/today/{sensorId}")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesOfToday(@PathVariable String sensorId) {
        return ResponseEntity.ok(this.temperatureService.getAllTemperaturesOfToday(sensorId));
    }

    @PostMapping("")
    public ResponseEntity<TemperatureDTO> createTemperature(@Valid @RequestBody TemperatureDTO temperatureDTO) {
        return new ResponseEntity<>(this.temperatureService.createTemperature(temperatureDTO), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TemperatureDTO> deleteTemperature(@NotNull @PathVariable String id) {
        return new ResponseEntity<>(this.temperatureService.deleteTemperature(id), HttpStatus.valueOf(200));
    }
}
