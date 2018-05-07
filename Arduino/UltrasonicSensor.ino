#include <HCSR04.h>
const int trigPin=12;
const int echoPin=13;

HCSR04 sensor(trigPin,echoPin);

void setup() {  
  Serial.begin(9600);
}

void loop() {
  float cm=sensor.ping(58,615);
  Serial.println(cm);
  delay(250);
}
