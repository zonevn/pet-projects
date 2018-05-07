int interval = 500;
int ledCtrl = 6;
int ledRecv = 5;
int req;

void setup() {  
  pinMode(ledCtrl,OUTPUT);
  pinMode(ledRecv,OUTPUT);
  Serial.begin(9600);  
}

void loop() {    
  digitalWrite(ledRecv,LOW);
  delay(interval);
  
  if(Serial.available()>0){
    
    digitalWrite(ledRecv,HIGH);
    delay(interval);
    
    req=Serial.read();
    Serial.flush();    
    switch(req){      
      case 0:
        Serial.println("LED Off");
        digitalWrite(ledCtrl,LOW);
        delay(interval);
        break;
      case  1:
        Serial.println("LED On");
        digitalWrite(ledCtrl,HIGH);        
        delay(interval);                
        break;
      case 2:
        Serial.println("LED Blinking");
        for(int i=0; i<5; i++){
          digitalWrite(ledCtrl,HIGH);
          delay(interval);
          digitalWrite(ledCtrl,LOW);
          delay(interval);          
        }
        Serial.println("LED Off");
       break;      
     }
  }
}
