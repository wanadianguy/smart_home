package com.insa.smarthomecontroller.api.repository;

import com.insa.smarthomecontroller.api.model.AverageTemperatures;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AverageTemperaturesRepository extends MongoRepository<AverageTemperatures, String> {

    AverageTemperatures findBySensorIdAndDate(String sensorId, LocalDate date);

    List<AverageTemperatures> findAllBySensorIdAndDateBetween(String sensorId,LocalDate startDate, LocalDate endDate);
}