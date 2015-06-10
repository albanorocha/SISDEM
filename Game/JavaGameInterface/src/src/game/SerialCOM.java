package src.game;

//This class:
// - Starts up the communication with the Arduino.
// - Reads the data coming in from the Arduino and
//   converts that data in to a useful form.
// - Closes communication with the Arduino.
//Code builds upon this great example:
//http://www.csc.kth.se/utbildning/kth/kurser/DH2400/interak06/SerialWork.java
//The addition being the conversion from incoming characters to numbers. 
//Load Libraries
import java.io.*;
import java.util.TooManyListenersException;

//Load RXTX Library
import gnu.io.*;

class SerialCOM implements SerialPortEventListener {

    static final int LENGTH = 4;
    int[] buffer = new int[LENGTH];

    //Used to in the process of converting the read in characters-
    //-first in to a string and then into a number.
    String rawStr = "";

    public int[] getBuffer() {
        return buffer;
    }

    //Declare serial port variable
    SerialPort mySerialPort;

    //Declare input steam
    InputStream in;
    boolean stop = false;

    public void start(String portName, int baudRate) {
        stop = false;
        try {
            //Finds and opens the port
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portName);
            mySerialPort = (SerialPort) portId.open("my_java_serial" + portName, 2000);
            System.out.println("Serial port found and opened");

            //configure the port
            try {
                mySerialPort.setSerialPortParams(baudRate,
                        mySerialPort.DATABITS_8,
                        mySerialPort.STOPBITS_1,
                        mySerialPort.PARITY_NONE);
                System.out.println("Serial port params set: " + baudRate);
            } catch (UnsupportedCommOperationException e) {
                System.out.println("Probably an unsupported Speed");
            }

            //establish stream for reading from the port
            try {
                in = mySerialPort.getInputStream();
            } catch (IOException e) {
                System.out.println("couldn't get streams");
            }

            // we could read from "in" in a separate thread, but the API gives us events
            try {
                mySerialPort.addEventListener(this);
                mySerialPort.notifyOnDataAvailable(true);
                System.out.println("Event listener added");
            } catch (TooManyListenersException e) {
                System.out.println("couldn't add listener");
            }
        } catch (Exception e) {
            System.out.println("Port in Use: " + e);
        }
    }

    //Used to close the serial port
    public void closeSerialPort() {
        try {
            in.close();
            stop = true;
            mySerialPort.close();
            System.out.println("Serial port closed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Reads the incoming data packets from Arduino. 
    @Override
    public void serialEvent(SerialPortEvent event) {

        //Reads in data while data is available
        while (event.getEventType() == SerialPortEvent.DATA_AVAILABLE && stop == false) {
            try {
            //------------------------------------------------------------------- 

                //Read in the available character
                char ch = (char) in.read();

                //If the read character is a letter this means that we have found an identifier.
                if (Character.isLetter(ch) == true && rawStr != "") {
                    //Convert the string containing all the characters since the last identifier into an integer
                    int value = Integer.parseInt(rawStr);

                    if (ch == 'A') {
                        buffer[0] = value;
                        //System.out.println("\nValue A is: " + value);
                    }

                    if (ch == 'B') {
                        buffer[1] = value;
                        //System.out.println("Value B is: " + value);
                    }

                    if (ch == 'C') {
                        buffer[2] = value;
                        //System.out.println("Value C is: " + value);
                    }

                    if (ch == 'D') {
                        buffer[3] = value;
                        //System.out.println("Value D is: " + value);
                    }

                    for (int i = 0; i < LENGTH; i++) {
                        System.out.print(buffer[i]);
                    }
                    System.out.println();
                    //Reset rawStr ready for the next reading
                    rawStr = ("");
                } else {
                    //Add incoming characters to a string.
                    //Only add characters to the string if they are digits. 
                    //When the arduino starts up the first characters it sends through are S-t-a-r-t- 
                    //and so to avoid adding these characters we only add characters if they are digits.

                    if (Character.isDigit(ch)) {
                        rawStr = (rawStr + Character.toString(ch));
                    } else {
                        //System.out.print(ch);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
