package com.insa.smarthomecontroller.api.controller;

import com.insa.smarthomecontroller.api.dto.AverageTemperaturesDTO;
import com.insa.smarthomecontroller.api.exception.AverageTemperaturesException;
import com.insa.smarthomecontroller.api.service.AverageTemperaturesService;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/averages-temperatures")
@CrossOrigin
public class AverageTemperaturesController {

    private final AverageTemperaturesService averageTemperaturesService;

    public AverageTemperaturesController(AverageTemperaturesService averageTemperaturesService) {
        this.averageTemperaturesService = averageTemperaturesService;
    }

    @GetMapping("{sensorId}")
    public ResponseEntity<List<AverageTemperaturesDTO>> getAverageTemperaturesOnSetDuration(@NotNull @PathVariable String sensorId, @NotNull @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @NotNull @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(this.averageTemperaturesService.getAllAverageTemperaturesOnSetDuration(sensorId, startDate, endDate), HttpStatus.valueOf(200));
    }
}
