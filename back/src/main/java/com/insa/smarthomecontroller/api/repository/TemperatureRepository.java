package com.insa.smarthomecontroller.api.repository;

import com.insa.smarthomecontroller.api.model.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, String> {

    Temperature findFirstBySensorIdOrderByDateDesc(String sensorId);

    @Query(value = "{'sensorId': ?0}", sort = "{ 'date': -1 }")
    List<Temperature> findLatestBySensorId(String sensorId);

    List<Temperature> findAllBySensorIdAndDateBetween(String sensorId, LocalDate startDate, LocalDate endDate);
}