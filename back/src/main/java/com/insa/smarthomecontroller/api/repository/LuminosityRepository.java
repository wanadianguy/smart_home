package com.insa.smarthomecontroller.api.repository;

import com.insa.smarthomecontroller.api.model.Luminosity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LuminosityRepository extends MongoRepository<Luminosity, String> {

    Luminosity findFirstBySensorIdOrderByDateDesc(String sensorId);

    @Query(value = "{'sensorId': ?0}", sort = "{ 'date': -1 }")
    List<Luminosity> findLatestBySensorId(String sensorId);

    List<Luminosity> findAllBySensorIdAndDateBetween(String sensorId, LocalDate startDate, LocalDate endDate);
}