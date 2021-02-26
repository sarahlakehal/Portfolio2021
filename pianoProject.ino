//Sarah Lakehal
//For Concordia University

#include <FastLED.h>
int DA = A0; // Pin for Analog Output - AO
int threshold = 200; //Set minimum threshold for LED lit
int sensorvalue = 0;
int counter = 0;

#define LED_PIN     2
#define NUM_LEDS    60

CRGB leds[NUM_LEDS];
CRGB led[NUM_LEDS];

void setup() {

  FastLED.addLeds<WS2812, LED_PIN, GRB>(leds, NUM_LEDS);
  for (int i = 29; i >= 0; i--) 
  {
     leds[i] = CRGB (0, 0, 0);
     leds[59-i] = CRGB (0,0,0);
     delay(40);
    FastLED.show();
  }
  Serial.begin(9600);
   pinMode(A0,INPUT);

}
void loop()
{
  sensorvalue = analogRead(DA);  //Read the analog value

  if(sensorvalue > threshold)
  {
    ++counter;
    for (int i = 0; i < counter; i++){
    if (i < 5)
      leds[counter] = CRGB (255, 0, 0);
        if (i >5 && i < 10)
      leds[counter] = CRGB (255, 127, 0);
        if (i >10 && i < 20)
      leds[counter] = CRGB (127, 255, 0);
        if (i >20 && i < 30)
      leds[counter] = CRGB (0, 255, 127);
        if (i >30 && i < 40)
      leds[counter] = CRGB (0, 255, 255);
        if (i >40 && i < 50)
      leds[counter] = CRGB (0, 0, 255);
        if (i >50 && i < 55)
      leds[counter] = CRGB (127, 0, 255);
        if (i >55 && i < 60)
      leds[counter] = CRGB (255, 0, 127);
    }
    if (counter > NUM_LEDS)
      counter = 0;
      else 
      counter++;

  }

  else
  {
     FastLED.clear();     
  }
  
 FastLED.show();
 delay(10);

  
  
 
}
