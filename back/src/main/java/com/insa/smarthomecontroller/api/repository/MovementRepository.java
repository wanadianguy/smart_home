package com.insa.smarthomecontroller.api.repository;

import com.insa.smarthomecontroller.api.model.Movement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementRepository extends MongoRepository<Movement, String> {

    Movement findFirstBySensorIdAndIsTriggeredTrueOrderByDateDesc(String sensorId);

    List<Movement> findAllBySensorIdAndDateBetweenAndIsTriggeredTrue(String sensorId, LocalDate startDate, LocalDate endDate);

    @Query(value = "{'sensorId': ?0}", sort = "{ 'date': -1 }")
    List<Movement> findLatestBySensorId(String sensorId);
}