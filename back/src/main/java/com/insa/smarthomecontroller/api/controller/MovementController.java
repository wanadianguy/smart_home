package com.insa.smarthomecontroller.api.controller;

import com.insa.smarthomecontroller.api.dto.MovementDTO;
import com.insa.smarthomecontroller.api.dto.SumMovementsDTO;
import com.insa.smarthomecontroller.api.service.MovementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movements")
@CrossOrigin
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/today/{sensorId}")
    public ResponseEntity<List<MovementDTO>> getMovementsOfToday(@NotNull @PathVariable String sensorId) {
        return ResponseEntity.ok(this.movementService.getAllMovementsOfToday(sensorId));

    }

    @GetMapping("/last/{sensorId}")
    public ResponseEntity<MovementDTO> getLastMovement(@NotNull @PathVariable String sensorId) {
        return ResponseEntity.ok(this.movementService.getLastMovement(sensorId));
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<List<SumMovementsDTO>> getAmountOfMovementOnSetDuration(@NotNull @PathVariable String sensorId, @NotNull @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @NotNull @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(this.movementService.getAmountOfMovementsOnSetDuration(sensorId, startDate, endDate));
    }

    @PostMapping("")
    public ResponseEntity<MovementDTO> createMovement(@Valid @RequestBody MovementDTO movementDTO) {
        return new ResponseEntity<>(this.movementService.createMovement(movementDTO), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovementDTO> deleteMovement(@NotNull @PathVariable String id) {
        return new ResponseEntity<>(this.movementService.deleteMovement(id), HttpStatus.valueOf(200));
    }
}
