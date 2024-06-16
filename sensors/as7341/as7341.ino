#include <Adafruit_AS7341.h>

Adafruit_AS7341 as7341;

#include <WiFi.h>
#include <MQTTClient.h>
#include <ArduinoJson.h>

#include "as7341.h"

const int MQTT_PORT = 1883;
const char MQTT_CLIENT_ID[] = "Spectral-esp32-001";
const char MQTT_USERNAME[] = "";
const char MQTT_PASSWORD[] = "";

// The MQTT topics that ESP32 should publish/subscribe
const char PUBLISH_TOPIC[] = "Spectral";
const char SUBSCRIBE_TOPIC[] = "Spectral-Control/Spectral-esp32-001";

bool isOn;

WiFiClient network;
MQTTClient mqtt = MQTTClient(256);

void setup() {
  Serial.begin(9600);

  // Wait for communication with the host computer serial monitor
  while (!Serial) {
    delay(1);
  }
  
  if (!as7341.begin()){
    Serial.println("Could not find AS7341");
    while (1) { delay(10); }
  }

  as7341.setATIME(100);
  as7341.setASTEP(999);
  as7341.setGain(AS7341_GAIN_256X);

  WiFi.setMinSecurity(WIFI_AUTH_WPA_PSK);
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  Serial.println("ESP32 - Connecting to Wi-Fi");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();

  connectToMQTT();

  isOn = true;
}

void connectToMQTT() {
  // Connect to the MQTT broker
  mqtt.begin(MQTT_BROKER_ADDRESS, MQTT_PORT, network);

  // Create a handler for incoming messages
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

  // Subscribe to a topic, the incoming messages are processed by messageHandler() function
  if (mqtt.subscribe(SUBSCRIBE_TOPIC))
    Serial.print("ESP32 - Subscribed to the topic: ");
  else
    Serial.print("ESP32 - Failed to subscribe to the topic: ");

  Serial.println(SUBSCRIBE_TOPIC);
  Serial.println("ESP32 - MQTT broker Connected!");
}

void sendToMQTT(StaticJsonDocument<200> message) {
  char messageBuffer[512];
  serializeJson(message, messageBuffer);

  mqtt.publish(PUBLISH_TOPIC, messageBuffer);

  Serial.println("ESP32 - sent to MQTT:");
  Serial.print("- topic: ");
  Serial.println(PUBLISH_TOPIC);
  Serial.print("- payload:");
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

/** 
  * read all the frequencies
  */
void readAS7341Data() {
  // Read all channels at the same time and store in as7341 object
  if (!as7341.readAllChannels()){
    Serial.println("Error reading all channels!");
    return;
  }

  // Create the message to send to the broker
  StaticJsonDocument<200> message;
  message["timestamp"] = millis();

  message["id"] = MQTT_CLIENT_ID;

  message["data"]["F1 415nm"] = as7341.getChannel(AS7341_CHANNEL_415nm_F1);
  message["data"]["F2 445nm"] = as7341.getChannel(AS7341_CHANNEL_445nm_F2);
  message["data"]["F3 480nm"] = as7341.getChannel(AS7341_CHANNEL_480nm_F3);
  message["data"]["F4 515nm"] = as7341.getChannel(AS7341_CHANNEL_515nm_F4);
  message["data"]["F5 555nm"] = as7341.getChannel(AS7341_CHANNEL_555nm_F5);
  message["data"]["F6 590nm"] = as7341.getChannel(AS7341_CHANNEL_590nm_F6);
  message["data"]["F7 630nm"] = as7341.getChannel(AS7341_CHANNEL_630nm_F7);
  message["data"]["F8 680nm"] = as7341.getChannel(AS7341_CHANNEL_680nm_F8);

  message["data"]["Clear"] = as7341.getChannel(AS7341_CHANNEL_CLEAR);
  message["data"]["Near IR"] = as7341.getChannel(AS7341_CHANNEL_NIR);

  sendToMQTT(message);

  // Print out the stored values for each channel
  Serial.print("F1 415nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_415nm_F1));
  Serial.print("F2 445nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_445nm_F2));
  Serial.print("F3 480nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_480nm_F3));
  Serial.print("F4 515nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_515nm_F4));
  Serial.print("F5 555nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_555nm_F5));
  Serial.print("F6 590nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_590nm_F6));
  Serial.print("F7 630nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_630nm_F7));
  Serial.print("F8 680nm : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_680nm_F8));

  Serial.print("Clear    : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_CLEAR));

  Serial.print("Near IR  : ");
  Serial.println(as7341.getChannel(AS7341_CHANNEL_NIR));

  Serial.println("");
}

void loop() {
  mqtt.loop();
  
  if(isOn)
    readAS7341Data();
  
  delay(10000);
}
