#include <WiFi.h>
#include <MQTTClient.h>
#include <ArduinoJson.h>
#include "laser.h"

const int MQTT_PORT = 1883;
const char MQTT_CLIENT_ID[] = "Movement-esp32-003";
const char MQTT_USERNAME[] = "";
const char MQTT_PASSWORD[] = "";

// The MQTT topics that ESP32 should publish/subscribe
const char PUBLISH_TOPIC[] = "Movement";
const char SUBSCRIBE_TOPIC[] = "Movement-Control/Movement-esp32-003"; 

bool isOn;

WiFiClient network;
MQTTClient mqtt = MQTTClient(256);

int LASER_DIN = 15; // digital OUT PIN number 15

StaticJsonDocument<200> message;

void connectToMQTT() {
  // Connect to the MQTT broker
  mqtt.begin(MQTT_BROKER_ADDRESS, MQTT_PORT, network);

  mqtt.onMessage(messageHandler);

  Serial.print("ESP32 - Connecting to MQTT broker");

  while (!mqtt.connect(MQTT_CLIENT_ID, MQTT_USERNAME, MQTT_PASSWORD)) {
    Serial.print(".");
    delay(100);
  }
  Serial.println();

  if (!mqtt.connected()) {
    Serial.println("ESP32 - MQTT broker Timeout!");
    return;
  }

  if (mqtt.subscribe(SUBSCRIBE_TOPIC))
    Serial.print("ESP32 - Subscribed to the topic: ");
  else
    Serial.print("ESP32 - Failed to subscribe to the topic: ");
  Serial.println(SUBSCRIBE_TOPIC);

  Serial.println("ESP32 - MQTT broker Connected!");
}

void sendToMQTT(StaticJsonDocument<200> message) {
  char messageBuffer[512];
  message["id"] = MQTT_CLIENT_ID;
  serializeJson(message, messageBuffer);

  mqtt.publish(PUBLISH_TOPIC, messageBuffer);

  Serial.println("ESP32 - sent to MQTT:");
  Serial.print("- topic: ");
  Serial.println(PUBLISH_TOPIC);
  Serial.println("- payload:");
  Serial.println(messageBuffer);
}

void messageHandler(String &topic, String &payload) {
  Serial.println("ESP32 - received from MQTT:");
  Serial.println("- topic: " + topic);
  Serial.println("- payload:");
  Serial.println(payload);

  if(payload == "True")
    isOn = true;
  else if (payload == "False")
    isOn = false;
}

void setup()
{
  pinMode(LASER_DIN, INPUT);
  Serial.begin(9600);

  WiFi.setMinSecurity(WIFI_AUTH_WPA_PSK);
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  connectToMQTT();

  isOn = true;
}

void readLaserData() {
  int laserState = digitalRead(LASER_DIN);
  message["timestamp"] = millis();

  if(laserState == LOW)
  {
    Serial.println("Obstacle !");
    message["obstacle"] = true;
  } else {
    Serial.println("NO obstacle");
    message["obstacle"] = false;
  }
}

void loop()
{
  mqtt.loop();

  readLaserData();
  if(isOn) {
    sendToMQTT(message);
  }
  
  delay(500);
}