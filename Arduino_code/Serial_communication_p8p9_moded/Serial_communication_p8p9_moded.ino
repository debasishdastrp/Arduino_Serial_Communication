
boolean p8=LOW,p9=LOW;
int e=8,n=9;
void setup() {
  Serial.begin(9600); // used for Communicating with Arduino IDE and Java Appliaction
  pinMode(e,OUTPUT);
  pinMode(n,OUTPUT);
  digitalWrite(e, LOW);
  digitalWrite(n, LOW);

}


void loop() {
  if (Serial.available() > 0) {
    byte incomingByte = 0;
    incomingByte = Serial.read();
    if (incomingByte != -1) {
      int p = (int)incomingByte;

      
      if(p==22)
        {
          delay(200);
          Serial.print("1");
          
        }
        else if(p==2)
        {
          //Triggering PIN 8          
          if(p8)
          {
          digitalWrite(e, LOW);
          delay(500);
          p8=LOW;
          Serial.print("0");
          } else{
          digitalWrite(e,HIGH);
          delay(500);
          p8=HIGH;
          Serial.print("1");
          }
        }
        else if(p==3)
        {
         //Triggering PIN 9
          if(p9)
          {
          digitalWrite(n, LOW);
          delay(500);
          p9=LOW;
          Serial.print("0");
          } else{
          digitalWrite(n, HIGH);
          delay(500);
          p9=HIGH;
          Serial.print("1");
          }
        }
        else
        {
          delay(300);
          Serial.print("5");
        }
    }
  }
}
