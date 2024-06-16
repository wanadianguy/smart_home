package com.insa.smarthomecontroller.mqtt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.insa.smarthomecontroller.mqtt.model.Sensor;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String> {
    Sensor findBySensorId(String sensorId);
}
