// ------------------------------------------------------------------------------------//
//*************************************************************************************//
//****************************  SISDEM - MODULE GAME V1.0  ****************************//
//*************************************************************************************//
// Author: Eduardo Augusto Morais Rodrigues                                            //
// Data: 05/06/2015                                                                    //
// ------------------------------------------------------------------------------------//

void setup(){
  Serial.begin(9600);  
}
 
void loop(){
  int confirmP1 = (analogRead(A0) > 1000) ? 1 : 0;
  int skipP1 = (analogRead(A1) < 500) ? 1 : 0;
  int confirmP2 = (analogRead(A2) > 1000) ? 1 : 0;
  int skipP2 = (analogRead(A3) < 500) ? 1 : 0;
  
  int dsw1P1 = digitalRead(10);
  int dsw2P1 = digitalRead(11);
  int dsw3P1 = digitalRead(12);
  int dsw4P1 = digitalRead(13);
  
  int dsw1P2 = digitalRead(6);
  int dsw2P2 = digitalRead(7);
  int dsw3P2 = digitalRead(8);
  int dsw4P2 = digitalRead(9);
  
  // Function to convert binary value from dip switches in an equivalent decimal value
  int A = dsw1P1*8 + dsw2P1*4 + dsw3P1*2 + dsw4P1;
  int B = dsw1P2*8 + dsw2P2*4 + dsw3P2*2 + dsw4P2;
  int C = skipP1*2 + confirmP1;  
  int D = skipP2*2 + confirmP2;  

   //We send the value coupled with an identifier character
   //that both marks the end of the value and what the value is.
  
   Serial.print(A);
   Serial.print("A");
   
   Serial.print(B);
   Serial.print("B");
   
   Serial.print(C);
   Serial.print("C");
   
   Serial.print(D);
   Serial.print("D");
   
   //Serial.println();
      
   //A delay to slow the program down to human pace.
   delay(250);                     
}
