package com.insa.smarthomecontroller.mqtt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.insa.smarthomecontroller.api.model.Luminosity;
import com.insa.smarthomecontroller.api.model.Movement;
import com.insa.smarthomecontroller.api.model.Temperature;
import com.insa.smarthomecontroller.api.repository.LuminosityRepository;
import com.insa.smarthomecontroller.api.repository.MovementRepository;
import com.insa.smarthomecontroller.api.repository.TemperatureRepository;
import com.insa.smarthomecontroller.mqtt.model.MovementMQTT;
import com.insa.smarthomecontroller.mqtt.model.Sensor;
import com.insa.smarthomecontroller.mqtt.model.SpectralMQTT;
import com.insa.smarthomecontroller.mqtt.model.TemperatureMQTT;
import com.insa.smarthomecontroller.mqtt.repository.SensorRepository;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Component
public class MqttManager {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.broker.port}")
    private int brokerPort;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private LuminosityRepository luminosityRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private TemperatureRepository temperatureRepository;

    private Mqtt5BlockingClient client;

    @Bean
    public void connectToBroker() {
        // Connect to the MQTT broker
        int i = 0;
        boolean connected = false;
        while (!connected && i <= 2) {
            try {
                System.out.println("Connecting to MQTT broker at " + brokerUrl + ":" + brokerPort);
                this.client = MqttClient.builder()
                        .useMqttVersion5()
                        .serverHost(brokerUrl)
                        .serverPort(brokerPort)
                        .identifier(UUID.randomUUID().toString())
                        .buildBlocking();

                this.client.connect();
                connected = true;
                break;
            } catch (Exception e) {
                i++;
                if (i < 2) {
                    System.out.println("Attempt to connect to MQTT broker failed");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("Connection to MQTT broker failed");
                }
            }
        }
    }

    @Bean
    public void subscribeToTopics() {
        if (this.client != null && this.client.getState().isConnected()) {
            System.out.println("Connected to MQTT broker");

            this.client.toAsync().subscribeWith().addSubscription().topicFilter("Temperature").applySubscription()
                    .callback((arg) -> {
                        // Get the message send by the publisher
                        String message = new String(arg.getPayloadAsBytes());
                        System.out.println("Temperature received: " + message);

                        // Transform the message into a json to read the values

                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            TemperatureMQTT temperature = mapper.readValue(message, TemperatureMQTT.class);

                            // Save the sensor in the database if it is not already in it
                            if (sensorRepository.findBySensorId(temperature.getId()) == null) {
                                sensorRepository.save(new Sensor(null, temperature.getId(), "Temperature", ""));
                            }

                            LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                            temperatureRepository.save(
                                    new Temperature(null, temperature.getId(), timestamp, temperature.getDegree()));

                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }).send();

            this.client.toAsync().subscribeWith().addSubscription().topicFilter("Movement").applySubscription()
                    .callback((arg) -> {
                        // Get the message send by the publisher
                        String message = new String(arg.getPayloadAsBytes());
                        System.out.println("Movement received: " + message);

                        // Transform the message into a json to read the values

                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            MovementMQTT movement = mapper.readValue(message, MovementMQTT.class);

                            // Save the sensor in the database if it is not already in it
                            if (sensorRepository.findBySensorId(movement.getId()) == null) {
                                sensorRepository.save(new Sensor(null, movement.getId(), "Movement", ""));
                            }

                            LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                            Boolean hasObstacle = movement.getObstacle();

                            // if (hasObstacle) {
                            // If there is an obstacle, save the movement in the database
                            movementRepository.save(new Movement(null, movement.getId(), timestamp, hasObstacle));
                            // }

                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }).send();

            this.client.toAsync().subscribeWith().addSubscription().topicFilter("Spectral/#").applySubscription()
                    .callback((arg) -> {
                        // Get the message send by the publisher
                        String message = new String(arg.getPayloadAsBytes());
                        System.out.println("Spectral received: " + message);

                        // Transform the message into a json to read the values

                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            SpectralMQTT spectral = mapper.readValue(message, SpectralMQTT.class);

                            // Save the sensor in the database if it is not already in it
                            if (sensorRepository.findBySensorId(spectral.getId()) == null) {
                                sensorRepository.save(new Sensor(null, spectral.getId(), "Luminosity", ""));
                            }

                            LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Europe/Paris"));

                            Double luminosity = spectral.getLuminosity();

                            luminosityRepository.save(new Luminosity(null, spectral.getId(), timestamp, luminosity));

                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                    }).send();
        }
    }

    public void sendToTopic(String topic, String message) {
        if (this.client.getState().isConnected()) {
            System.out.println("Sending message to topic " + topic + ": " + message);
            this.client.publishWith().topic(topic).payload(message.getBytes()).send();
        }
    }
}
