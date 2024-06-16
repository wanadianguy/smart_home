package com.insa.smarthomecontroller.api.controller;

import com.insa.smarthomecontroller.api.dto.LuminosityDTO;
import com.insa.smarthomecontroller.api.service.LuminosityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/luminosities")
@CrossOrigin
public class LuminosityController {

    private final LuminosityService luminosityService;

    public LuminosityController(LuminosityService luminosityService) {
        this.luminosityService = luminosityService;
    }

    @GetMapping("/current/{sensorId}")
    public ResponseEntity<LuminosityDTO> getCurrentLuminosity(@NotNull @PathVariable String sensorId) {
        return ResponseEntity.ok(this.luminosityService.getCurrentLuminosity(sensorId));
    }

    @GetMapping("/today/{sensorId}")
    public ResponseEntity<List<LuminosityDTO>> getLuminositiesOfToday(@NotNull @PathVariable String sensorId) {
        return ResponseEntity.ok(this.luminosityService.getAllLuminositiesOfToday(sensorId));
    }

    @PostMapping("")
    public ResponseEntity<LuminosityDTO> createLuminosity(@Valid @RequestBody LuminosityDTO luminosityDTO) {
        return new ResponseEntity<>(this.luminosityService.createLuminosity(luminosityDTO), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LuminosityDTO> deleteLuminosity(@NotNull @PathVariable String id) {
        return new ResponseEntity<>(this.luminosityService.deleteLuminosity(id), HttpStatus.valueOf(200));
    }
}
