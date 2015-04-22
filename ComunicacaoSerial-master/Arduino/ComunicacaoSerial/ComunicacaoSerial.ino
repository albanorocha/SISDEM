int led1 = 6; 
int led2 = 7;
int led3 = 8;
int led4 = 9;
int led5 = 10;
int led6 = 11;
int led7 = 12;

int dado; //variável que receberá os dados da porta serial

void setup(){
  Serial.begin(9600);//frequência da porta serial
  pinMode(led1,OUTPUT); //define o pino o ledPin como saída
  pinMode(led2,OUTPUT);
  pinMode(led3,OUTPUT);
  pinMode(led4,OUTPUT);     
  pinMode(led5,OUTPUT);
  pinMode(led6,OUTPUT);
  pinMode(led7,OUTPUT);
}

void loop(){
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial      
      dado = Serial.read();//lê os dados da porta serial
      
      digitalWrite(led1,LOW);
      digitalWrite(led2,LOW);
      digitalWrite(led3,LOW);
      digitalWrite(led4,LOW);
      digitalWrite(led5,LOW);
      digitalWrite(led6,LOW);
      digitalWrite(led7,LOW);      
      
      switch(dado){
        case 1:
           digitalWrite(led1,HIGH); 
           break;
        case 2:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           break;
        case 3:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           digitalWrite(led3,HIGH); 
           break;
        case 4:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           digitalWrite(led3,HIGH); 
           digitalWrite(led4,HIGH);                    
           break;
        case 5:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           digitalWrite(led3,HIGH); 
           digitalWrite(led4,HIGH);                    
           digitalWrite(led5,HIGH); 
           break;
        case 6:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           digitalWrite(led3,HIGH); 
           digitalWrite(led4,HIGH);                    
           digitalWrite(led5,HIGH); 
           digitalWrite(led6,HIGH); 
           break;
        case 7:
           digitalWrite(led1,HIGH); 
           digitalWrite(led2,HIGH);            
           digitalWrite(led3,HIGH); 
           digitalWrite(led4,HIGH);                    
           digitalWrite(led5,HIGH); 
           digitalWrite(led6,HIGH); 
           digitalWrite(led7,HIGH);            
           break; 
        default:
           break;   
    }
  }
}



