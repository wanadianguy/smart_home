package com.insa.smarthomecontroller.mqtt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insa.smarthomecontroller.mqtt.MqttManager;
import com.insa.smarthomecontroller.mqtt.service.SensorService;

@RestController
@RequestMapping("/mqtt")
@CrossOrigin
public class MqttController {

    @Autowired
    private MqttManager mqttManager;

    @Autowired
    private SensorService sensorService;

    @GetMapping("/{type}/toggle/{id}")
    public ResponseEntity<String> toggleSensor(@PathVariable String type, @PathVariable String id) {
        Optional<String> topic = Optional.empty();
        switch (type) {
            case "Movement":
                topic = Optional.of("Movement-Control");
                break;
            case "Luminosity":
                topic = Optional.of("Spectral-Control");
                break;
            case "Temperature":
                topic = Optional.of("Temperature-Control");
                break;
            default:
                return new ResponseEntity<>("", HttpStatus.valueOf(404));
        }

        if (topic.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.valueOf(404));
        }

        Optional<Boolean> statusOpt = sensorService.getSensorStatus(type, id);

        if (statusOpt.isPresent()) {
            mqttManager.sendToTopic(topic.get() + "/" + id, statusOpt.get() ? "False" : "True");
            return new ResponseEntity<>("", HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>("", HttpStatus.valueOf(404));
        }
    }

}
