package com.insa.smarthomecontroller.api.service;

import com.insa.smarthomecontroller.api.dto.SumMovementsDTO;
import com.insa.smarthomecontroller.api.mapper.MovementMapper;
import com.insa.smarthomecontroller.api.dto.MovementDTO;
import com.insa.smarthomecontroller.api.exception.MovementException;
import com.insa.smarthomecontroller.api.mapper.MovementMapperImpl;
import com.insa.smarthomecontroller.api.model.Movement;
import com.insa.smarthomecontroller.api.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementMapper movementMapper;
    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
        this.movementMapper = new MovementMapperImpl();
    }

    public MovementDTO getLastMovement(String sensorId) throws MovementException {
        Movement lastMovement = movementRepository.findFirstBySensorIdAndIsTriggeredTrueOrderByDateDesc(sensorId);

        if (Objects.isNull(lastMovement)) {
            throw new MovementException("No movement found.");
        }
        return movementMapper.mapMovementDTO(lastMovement);
    }

    public List<MovementDTO> getAllMovementsOfToday(String sensorId) throws MovementException {
        LocalDate today = LocalDate.now();
        List<Movement> movements = movementRepository.findAllBySensorIdAndDateBetweenAndIsTriggeredTrue(sensorId, today,
                today.plusDays(1));

        if (movements.isEmpty()) {
            throw new MovementException("No movement found for today.");
        }

        return movements.stream().map(movementMapper::mapMovementDTO).sorted(Comparator.comparing(MovementDTO::getDate))
                .toList();
    }

    public List<SumMovementsDTO> getAmountOfMovementsOnSetDuration(String sensorId, LocalDate startDate,
                                                                   LocalDate endDate) throws MovementException {
        List<Movement> movements = movementRepository.findAllBySensorIdAndDateBetweenAndIsTriggeredTrue(sensorId,
                startDate, endDate.plusDays(1));

        if (movements.isEmpty()) {
            throw new MovementException("No movement found for the specified duration.");
        }

        Map<LocalDate, Long> movementsCountByDate = movements.stream()
                .collect(Collectors.groupingBy(movement -> movement.getDate().toLocalDate(), Collectors.counting()));

        return movementsCountByDate.entrySet().stream()
                .map(entry -> SumMovementsDTO.builder()
                        .sensorId(sensorId)
                        .date(entry.getKey())
                        .numberOfMovements(entry.getValue().intValue())
                        .build())
                .collect(Collectors.toList());
    }

    public MovementDTO createMovement(MovementDTO movementDTO) {
        Movement movement = movementMapper.mapMovement(movementDTO);
        Movement savedMovement = movementRepository.save(movement);

        return movementMapper.mapMovementDTO(savedMovement);
    }

    public MovementDTO deleteMovement(String id) throws MovementException {
        Optional<Movement> optionalMovement = movementRepository.findById(id);

        if (optionalMovement.isEmpty()) {
            throw new MovementException("Movement not found with id: " + id);
        }

        Movement movementToDelete = optionalMovement.get();
        movementRepository.delete(movementToDelete);
        return movementMapper.mapMovementDTO(movementToDelete);
    }
}
