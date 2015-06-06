// ------------------------------------------------------------------------------------//
//*************************************************************************************//
//*************************  SISDEM - MÓDULO ACADÊMICO V1.0  **************************//
//*************************************************************************************//
// Author: Eduardo Augusto Morais Rodrigues                                            //
// Data: 10/03/2015                                                                    //
// ------------------------------------------------------------------------------------//


//-------------------- CONSTANTS AND VARIABLES DECLARATIONS ---------------------------//
// Constants for DipSwitches (Inputs)
int const DSW_1 = 6;
int const DSW_5 = 5;
int const DSW_6 = 4;
int const DSW_7 = 3;
int const DSW_8 = 2;

// Constants to divert program's flow
int const BIN_MODE = 0;
int const GATES_MODE = 1;
int const BY_HARDWARE = 0;
int const BY_SOFTWARE = 1;

// Costants for pushbuttons  (Inputs)
// ArduinoUNO
int const PSB_1 = A0;
int const PSB_2 = A1;

// Constants for LEDs (Outputs)
int const LED1 = 7;
int const LED2 = 8;
int const LED3 = 9;
int const LED4 = 10;
int const LED5 = 11;
int const LED6 = 12;
int const LED7 = 13;

// Constant to define buffer's length
int const LENGTH = 10;

// Auxiliar variables
int decimal_value, operational_mode, operational_control, dsw_bit0, dsw_bit1, dsw_bit2, dsw_bit3, push_b1, push_b2, gate_result = 0;
int toSendDataBuffer[LENGTH], receiveDataBuffer[LENGTH]; 
//-------------------------------------------------------------------------------------//


// Set and startup system
void setup() {
  // Dip Switches (System's inputs)
  //pinMode(DSW_1, INPUT); // operational_control (DipSwitch_1)
  pinMode(DSW_1, INPUT); // operational_mode (DipSwitch_1)
  pinMode(DSW_5, INPUT); // operational_control (DipSwitch_1)
  pinMode(DSW_6, INPUT); // Bit 0 (DipSwitch_8)
  pinMode(DSW_7, INPUT); // Bit 1 (DipSwitch_7)
  pinMode(DSW_8, INPUT); // Bit 2 (DipSwitch_6) 

  // LEDS (System's outputs)
  pinMode(LED1, OUTPUT); 
  pinMode(LED2, OUTPUT); 
  pinMode(LED3, OUTPUT); 
  pinMode(LED4, OUTPUT);
  pinMode(LED5, OUTPUT);
  pinMode(LED6, OUTPUT);
  pinMode(LED7, OUTPUT);
  
  // Starts Serial Communication with 9600 baud rate value
  Serial.begin(9600);
}

// Função para converter o "valor" binário de entrada das dipswitchs em um valor decimal equivalente
int myBin2Dec(){
  return(dsw_bit3*(8) + dsw_bit2*(4) + dsw_bit1*(2) + dsw_bit0);
}

void writeData(){  
  toSendDataBuffer[0] = operational_mode;
  toSendDataBuffer[1] = operational_control;
  toSendDataBuffer[2] = dsw_bit0;
  toSendDataBuffer[3] = dsw_bit1;
  toSendDataBuffer[4] = dsw_bit2;
  toSendDataBuffer[5] = dsw_bit3;
  toSendDataBuffer[6] = decimal_value;
  toSendDataBuffer[7] = push_b1;
  toSendDataBuffer[8] = push_b2;
  toSendDataBuffer[9] = gate_result;
  
  for(int i = 0; i < LENGTH; i++){
    Serial.write(toSendDataBuffer[i]);
  }
}

void readData(){
  //receiveDataBuffer[LENGTH];  
    
  for(int i = 0; i < LENGTH; i++){
    receiveDataBuffer[i] = Serial.read();
  }

  // Filling variables
  operational_mode = receiveDataBuffer[0];
  operational_control = receiveDataBuffer[1];
  dsw_bit0 = receiveDataBuffer[2];
  dsw_bit1 = receiveDataBuffer[3];
  dsw_bit2 = receiveDataBuffer[4];
  dsw_bit3 = receiveDataBuffer[5];
  decimal_value = receiveDataBuffer[6];
  push_b1 = receiveDataBuffer[7];
  push_b2 = receiveDataBuffer[8];
  gate_result = receiveDataBuffer[9];
}

// Read inputs' values of the system
void readInputs(){
  //operational_control = digitalRead(DSW_1);
  operational_mode = digitalRead(DSW_1);
  dsw_bit0 = digitalRead(DSW_8);
  dsw_bit1 = digitalRead(DSW_7);
  dsw_bit2 = digitalRead(DSW_6);
  dsw_bit2 = digitalRead(DSW_5);
  push_b1 = analogRead(PSB_1);
  push_b2 = analogRead(PSB_2);
}

void allLedsOFF(){
  digitalWrite(LED1, LOW);
  digitalWrite(LED2, LOW);  
  digitalWrite(LED3, LOW);
  digitalWrite(LED4, LOW);
  digitalWrite(LED5, LOW);
  digitalWrite(LED6, LOW);
  digitalWrite(LED7, LOW);
}

void allLedsON(){
  digitalWrite(LED1, HIGH);      
  digitalWrite(LED2, HIGH);
  digitalWrite(LED3, HIGH);          
  digitalWrite(LED4, HIGH); 
  digitalWrite(LED5, HIGH);       
  digitalWrite(LED6, HIGH);
  digitalWrite(LED7, HIGH);   
}

void activeGates(){
  allLedsOFF();

  // Redefine analogic buttons' values with '0' or '1'
  push_b1 = (push_b1 > 1020) ? 1 : 0;
  push_b2 = (push_b2 > 1020) ? 1 : 0;
  //push_b2 = (push_b2 < 500) ? 1 : 0; // Uncomment this line to use the "BIG" switch
    
  //Serial.println(push_b1);
  switch(decimal_value){
    // NOT
    case 0:
      gate_result = !(push_b1);
      break;
    // AND
    case 1:
      gate_result = push_b1 & push_b2;
      break;
    // OR
    case 2:
      gate_result = push_b1 | push_b2;   
      break;     
    // NOR
    case 3:
      gate_result = !(push_b1 | push_b2);   
      break;         
    // NAND
    case 4:
      gate_result = !(push_b1 | push_b2);
      break;
    // XOR
    case 5:
      gate_result = (push_b1 != push_b2) ? 1 : 0;
      break;
    // XNOR
    case 6:    
      gate_result = (push_b1 == push_b2) ? 1 : 0;
      break;
    // ERROR   
    default:
      error();
      break;
  }
  
  // Active led1 to indicate gate output
  digitalWrite(LED1, gate_result);      
}

void activeLEDS(){
  allLedsOFF();
  
  switch(decimal_value){
    case 1:
      digitalWrite(LED1, HIGH);      
      break;
            
    case 2:
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      break;
            
    case 3:
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);                   
      break;
           
    case 4:
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);          
      digitalWrite(LED4, HIGH); 
      break;
         
    case 5:
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);          
      digitalWrite(LED4, HIGH); 
      digitalWrite(LED5, HIGH);              
      break;    

    case 6:
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);          
      digitalWrite(LED4, HIGH); 
      digitalWrite(LED5, HIGH);       
      digitalWrite(LED6, HIGH);
      break;    

    case 7:
      allLedsON();  
      break;
      
    default:
      break;    
  }
}

void error(){
  allLedsON();
  delay(100);
  allLedsOFF();
  delay(300);
}

// Main Loop
void loop() {
  
 /* 
  if(operational_control == BY_SOFTWARE){
    readData();     // Read inputs from aplication
  }else{
    readInputs();   // Read inputs from hardware
  }*/
  
  // Write data to aplication (Always writes data to aplication for refreshing high level interface)
  //writeData();
  
  readInputs();
  
  // Rescues and converts decimal value, represented in binary, from dipswitches
  decimal_value = myBin2Dec();

  // Flow's control
  switch (operational_mode) {
    allLedsOFF();    
    
    // Bin Mode - (0)
    case BIN_MODE:    
      activeLEDS();             
      break;

    // Gates Mode - (1)
    case GATES_MODE:
      activeGates();
      break;

    //  Error Mode
    default:
      error();
      break;
  }
}

