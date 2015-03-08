// Configurações e inicialização do sistema
void setup() {

  // Dip Switches (Entradas do Sistema)
  pinMode(2, INPUT); // Modo (DipSwitch_8)
  pinMode(5, INPUT); // Bit 2 (DipSwitch_1)
  pinMode(4, INPUT); // Bit 1 (DipSwitch_2)  
  pinMode(3, INPUT); // Bit 0 (DipSwitch_3)

  // LEDS (Saídas do Sistema)
  pinMode(6, OUTPUT); // LED1
  pinMode(7, OUTPUT); // LED2
  pinMode(8, OUTPUT); // LE33
  pinMode(9, OUTPUT); // LED4
  pinMode(10, OUTPUT);// LED5
  pinMode(11, OUTPUT);// LED6
  pinMode(12, OUTPUT);// LED7
  
  // Inicialização da comunicação serial com baud rate de 9600
  Serial.begin(9600);
}

// Função para converter o "valor" binário de entrada das dipswitchs em um valor decimal equivalente
int my_bin2dec(int dsw1, int dsw2, int dsw3){
  return(dsw1*(4) + dsw2*(2) + dsw3);
}

void activeLEDS(int decimal, int LED1, int LED2, int LED3, int LED4, int LED5, int LED6, int LED7){
  digitalWrite(LED1, LOW);
  digitalWrite(LED2, LOW);  
  digitalWrite(LED3, LOW);
  digitalWrite(LED4, LOW);
  digitalWrite(LED5, LOW);
  digitalWrite(LED6, LOW);
  digitalWrite(LED7, LOW);
  
  switch(decimal){
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
      digitalWrite(LED1, HIGH);      
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);          
      digitalWrite(LED4, HIGH); 
      digitalWrite(LED5, HIGH);       
      digitalWrite(LED6, HIGH);
      digitalWrite(LED7, HIGH);         
      break;
  
    default:
      break;    
  }  
}

// Laço principal
void loop() {
  
  // Constantes para DipSwitchs (Entradas)
  int const DSW_MODO = 2;
  int const DSW_1 = 5;
  int const DSW_2 = 4;
  int const DSW_3 = 3;

  // Constantes para os LEDs (Saídas)
  int const LED1 = 6;
  int const LED2 = 7;
  int const LED3 = 8;
  int const LED4 = 9;
  int const LED5 = 10;
  int const LED6 = 11;
  int const LED7 = 12;
  
  // Variáveis auxiliares
  int modo = digitalRead(DSW_MODO);
  int dsw1, dsw2, dsw3;
  dsw1 = digitalRead(DSW_1);
  dsw2 = digitalRead(DSW_2);
  dsw3 = digitalRead(DSW_3);
  int decimalValue = my_bin2dec(dsw1, dsw2, dsw3); // Resgata valor decimal equivalente, representado em binário, nas dipswitchs
  // Serial.print(decimalValue);

  switch (modo) {
    // Bin Mode - (00)
    case 0:    
        activeLEDS(decimalValue, LED1, LED2, LED3, LED4, LED5, LED6, LED7);             
      break;

    // Gates Mode - (01)
    case 1:
      break;

    // Modo de erro
    default:
      break;
  }
}

