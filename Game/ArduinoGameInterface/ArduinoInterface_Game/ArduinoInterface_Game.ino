// ------------------------------------------------------------------------------------//
//*************************************************************************************//
//****************************  SISDEM - MODULE GAME V1.0  ****************************//
//*************************************************************************************//
// Author: Eduardo Augusto Morais Rodrigues                                            //
// Data: 05/06/2015                                                                    //
// ------------------------------------------------------------------------------------//


//-------------------- CONSTANTS AND VARIABLES DECLARATIONS ---------------------------//
// Constants for DipSwitches (Inputs)
int const DSW_1 = 13;
int const DSW_2 = 12;
int const DSW_3 = 11;
int const DSW_4 = 10;
int const DSW_5 = 9;
int const DSW_6 = 8;
int const DSW_7 = 7;
int const DSW_8 = 6;

// Costants for pushbuttons  (Inputs)
int const PSB_1 = A0;
int const PSB_2 = A1;
int const PSB_3 = A2;
int const PSB_4 = A3;

// Constant to define buffer's length 
int const LENGTH_BUFFER = 14;

// Controllers variables
int decimal_value_P1, decimal_value_P2 = 0;
int dsw_P1[4], dsw_P2[4];
int pushB_confirm_P1, pushB_skip_P1 = 0;
int pushB_confirm_P2, pushB_skip_P2 = 0;

// Buffer
//int toSentDataBuffer[LENGTH];
byte toSentDataBuffer[LENGTH_BUFFER];
//-------------------------------------------------------------------------------------//


// Set and startup system
void setup() {
  // PLAYER 01
  pinMode(DSW_1, INPUT); // Bit 0 (DipSwitch_1)
  pinMode(DSW_2, INPUT); // Bit 1 (DipSwitch_2)
  pinMode(DSW_3, INPUT); // Bit 2 (DipSwitch_3)
  pinMode(DSW_4, INPUT); // Bit 3 (DipSwitch_4)

  // PLAYER 02
  pinMode(DSW_5, INPUT); // Bit 0 (DipSwitch_5)
  pinMode(DSW_6, INPUT); // Bit 1 (DipSwitch_6)
  pinMode(DSW_7, INPUT); // Bit 2 (DipSwitch_7) 
  pinMode(DSW_8, INPUT); // Bit 3 (DipSwitch_8)
  
  // Starts Serial Communication with 9600 baud rate value
  Serial.begin(9600);
  //Serial.begin(57600);
}

// Function to convert binary value from dip switches in an equivalent decimal value
int myBin2Dec(int binValue[4]){
  return(binValue[3]*(8) + binValue[2]*(4) + binValue[1]*(2) + binValue[0]);
}

// Read inputs' values from hardware components
void readInputs(){
  int n_samples = 10;
  
  // PLAYER 01  
  dsw_P1[0] = digitalRead(DSW_1);
  dsw_P1[1] = digitalRead(DSW_2);
  dsw_P1[2] = digitalRead(DSW_3);
  dsw_P1[3] = digitalRead(DSW_4);
  // Calc media
  for(int i = 0; i < n_samples; i++){  
    pushB_confirm_P1 += analogRead(PSB_1);
    pushB_skip_P1 += analogRead(PSB_2);
  }
  pushB_confirm_P1 /= n_samples;  
  pushB_skip_P1 /= n_samples;
  
  // PLAYER 02
  dsw_P2[0] = digitalRead(DSW_5);
  dsw_P2[1] = digitalRead(DSW_6);
  dsw_P2[2] = digitalRead(DSW_7);
  dsw_P2[3] = digitalRead(DSW_8);
  // Calc media
  for(int i = 0; i < n_samples; i++){    
    pushB_confirm_P2 += analogRead(PSB_3);
    pushB_skip_P2 += analogRead(PSB_4);
  }
  pushB_confirm_P2 /= n_samples;
  pushB_skip_P2 /= n_samples;
}

void writeData(){  
  // PLAYER 01
  toSentDataBuffer[0] = decimal_value_P1;
  toSentDataBuffer[1] = dsw_P1[0];
  toSentDataBuffer[2] = dsw_P1[1];
  toSentDataBuffer[3] = dsw_P1[2];
  toSentDataBuffer[4] = dsw_P1[3];
  toSentDataBuffer[5] = pushB_confirm_P1;
  toSentDataBuffer[6] = pushB_skip_P1;

  // PLAYER 02
  toSentDataBuffer[7] = decimal_value_P2;
  toSentDataBuffer[8] = dsw_P2[0];
  toSentDataBuffer[9] = dsw_P2[1];
  toSentDataBuffer[10] = dsw_P2[2];
  toSentDataBuffer[11] = dsw_P2[3];  
  toSentDataBuffer[12] = pushB_confirm_P2;
  toSentDataBuffer[13] = pushB_skip_P2;

  Serial.write(toSentDataBuffer, LENGTH_BUFFER);
  
  //for(int i = 0; i < LENGTH_BUFFER; i++){
    //Serial.write(toSentDataBuffer[i]);
    //Serial.println(toSentDataBuffer[i]);
  //}
  //Serial.println("---------------------------- FIM -----------------------------------");
  
}

void defineButtonsBinary(){
  /* PAY ATENTION WHEN USING THE 'BIG' SWITCH IN HARDWARE BECAUSE THE SECOND SWITCH (RED) WORKS IN ACTIVE LOW MODE*/
  
  // PLAYER 01
  pushB_confirm_P1 = (pushB_confirm_P1 > 1000) ? 1 : 0;
  //pushB_skip_P1 = (pushB_skip_P1 > 1000) ? 1 : 0;
  pushB_skip_P1 = (pushB_skip_P1 < 500) ? 1 : 0;
  // PLAYER 02
  pushB_confirm_P2 = (pushB_confirm_P2 > 1000) ? 1 : 0;
  //pushB_skip_P2 = (pushB_skip_P2 > 1000) ? 1 : 0;
  pushB_skip_P2 = (pushB_skip_P2 < 500) ? 1 : 0;      
}

// Main Loop
void loop() {  
  // Read system's inputs
  readInputs();
  
  // Converts analogic[0..1023] to digital[0..1] value
  defineButtonsBinary();
  
  /* Rescues and converts decimal values, represented in binary, from dipswitches */
  // PLAYER 01
  decimal_value_P1 = myBin2Dec(dsw_P1);
  // PLAYER 02
  decimal_value_P2 = myBin2Dec(dsw_P2);

  // Writes values through serial communication to update software interface
  writeData();  
  
  //
  delay(100);
}

