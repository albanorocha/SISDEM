/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class SerialCOM {

    /**
     * Object to control Serial Communication
     */
    SerialPort serialPort;
    int data = 0;
    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static int DATA_RATE;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
        "/dev/ttyUSB0", // Linux
        "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8" // Windows
    };

    public SerialCOM(int data_rate){
        DATA_RATE = data_rate;
        initialize();
    }
        
    /**
     * Method to verify COM Port communication
     */
    private void initialize() {
        // the next line is for Raspberry Pi and 
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t1=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            JOptionPane.showMessageDialog(null, "Could not find COM port.", "Error", 0);
            System.exit(0);
            //System.out.println("Could not find COM port.");
            //return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            try {
                // add event listeners
                serialPort.addEventListener((SerialPortEventListener) this);
            } catch (TooManyListenersException ex) {
                Logger.getLogger(SerialCOM.class.getName()).log(Level.SEVERE, null, ex);
            }
            serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException | UnsupportedCommOperationException | IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Method to close Serial COM Port
     */
    public void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * @param data - Value to be sent through COM Port
     */
    public void writeData(int data) {
        try {
            output.write(data);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "It wasn't possible send data. ", "Error sending data", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Method to read bytes from serial COM port
     *
     * @return
     */
    public int readData() {
        try {
            return (input.read());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "It wasn't possible read data.", "Receive Data", JOptionPane.PLAIN_MESSAGE);
            return -1;
        }
    }
}
