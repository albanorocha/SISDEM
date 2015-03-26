// Configurações e inicialização do sistema
void setup() {

  // Dip Switches e push Button (Entradas do Sistema)
  pinMode(2, INPUT); // bitModo0 (DipSwitch_1)
  pinMode(3, INPUT); // bitModo1 (DipSwitch_2)
  pinMode(4, INPUT); // bitModo2 (DipSwitch_3)
  pinMode(5, INPUT); // bitModo3 (DipSwitch_4)
  pinMode(6, INPUT); // bitModo4 (DipSwitch_5)
  
  pinMode(A0, INPUT); // bitDate0 (PushButton 1) 
  pinMode(A1, INPUT); // bitDate1 (PushButton 2)  
  pinMode(A2, INPUT); // bitDate2 (PushButton 3)

  // LEDS (Saídas do Sistema)
  pinMode(7, OUTPUT); // LED1
  pinMode(8, OUTPUT); // LED2
  pinMode(9, OUTPUT); // LE33
  pinMode(10, OUTPUT);// LED4
  pinMode(11, OUTPUT);// LED5
  pinMode(12, OUTPUT);// LED6
  pinMode(13, OUTPUT);// LED7
  
  // Inicialização da comunicação serial com baud rate de 9600
  //Serial.begin(9600);
}

// Função para converter o "valor" binário de entrada das dipswitchs em um valor decimal equivalente
int my_bin2dec(int data0, int data1, int data2){
  return(data0*(4) + data1*(2) + data2);
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
    digitalWrite(LED1, LOW);
    digitalWrite(LED2, LOW);  
    digitalWrite(LED3, LOW);
    digitalWrite(LED4, LOW);
    digitalWrite(LED5, LOW);
    digitalWrite(LED6, LOW);
    digitalWrite(LED7, LOW);
      break;    
  }  
}

// Laço principal
void loop() {
  
  // Constantes para DipSwitchs (Entradas)
  byte DSW_MODO0 = digitalRead(2);
  byte DSW_MODO1 = digitalRead(3);
  byte DSW_MODO2 = digitalRead(4);
  byte DSW_MODO3 = digitalRead(5);
  byte DSW_MODO4 = digitalRead(6);
  
  //Dados de entrada
  byte Data0 = digitalRead(A0);
  byte Data1 = digitalRead(A1);
  byte Data2 = digitalRead(A2);

  // Constantes para os LEDs (Saídas)
  byte LED1 = 7;
  byte LED2 = 8;
  byte LED3 = 9;
  byte LED4 = 10;
  byte LED5 = 11;
  byte LED6 = 12;
  byte LED7 = 13;
  
  // Variáveis auxiliares
  //int modo1 = digitalRead(DSW_MODO1), modo2 = digitalRead(DSW_MODO2);
  //int dsw1, dsw2, dsw3;
  //dsw1 = digitalRead(DSW_1);
  //dsw2 = digitalRead(DSW_2);
  //dsw3 = digitalRead(DSW_3);
  
  int decimalValue = my_bin2dec(Data0, Data1, Data2); // Resgata valor decimal equivalente, representado em binário, nas dipswitchs

  
    if(DSW_MODO0==LOW && DSW_MODO1==LOW){
    // Bin Mode - (00) 
        activeLEDS(decimalValue, LED1, LED2, LED3, LED4, LED5, LED6, LED7);
        decimalValue=0;        
    }
    else if(DSW_MODO0==LOW && DSW_MODO1==HIGH){
    // Gates Mode - (01)

      //porta "E"    
      if (DSW_MODO2==LOW && DSW_MODO3==LOW && DSW_MODO4==LOW){
          if(Data0==HIGH && Data1==HIGH){
            digitalWrite(13,HIGH);
          }
          else digitalWrite(13, LOW);
      }
      
      //Porta "OU"
      else if (DSW_MODO2== HIGH && DSW_MODO3==LOW && DSW_MODO4==LOW ){
        
       if(Data0==HIGH || Data1==HIGH) digitalWrite(13,HIGH);
       else digitalWrite(13, LOW); 
      }
      
      //Porta "NÂO"
      else if (DSW_MODO2==LOW && DSW_MODO3==HIGH && DSW_MODO4==LOW){
        
       if(Data0==HIGH) digitalWrite(13,LOW);
       else digitalWrite(13, HIGH); 
      }
      
      //Porta "NAND"
      else if (DSW_MODO2==HIGH && DSW_MODO3==HIGH && DSW_MODO4==LOW){
        
       if(Data0==HIGH && Data1==HIGH) digitalWrite(13,LOW);
       else digitalWrite(13, HIGH); 
      }
      
      //Porta "NOR"
      else if (DSW_MODO2==LOW && DSW_MODO3==LOW && DSW_MODO4==HIGH){
        
       if(Data0==HIGH || Data1==HIGH) digitalWrite(13,LOW);
       else digitalWrite(13, HIGH); 
      }
      
      //Porta "EX-OR" ou "OU EXCLUSIVO" ou "XOR"
      else if (DSW_MODO2==HIGH && DSW_MODO3==LOW && DSW_MODO4==HIGH){
        
       if(Data0!=Data1) digitalWrite(13,HIGH);
       else digitalWrite(13, LOW);
      }
    
      //Porta "XNOR" ou "COINCIDENCIA"
      else if (DSW_MODO2==LOW && DSW_MODO3==HIGH && DSW_MODO4==HIGH){
        
       if(Data0==Data1) digitalWrite(13,HIGH);
       else digitalWrite(13, LOW);
      }
    
  }

}

