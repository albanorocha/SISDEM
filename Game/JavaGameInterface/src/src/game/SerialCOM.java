/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

/**
 *
 * @author Eduardo
 */
public class SerialCOM implements SerialPortEventListener {

    SerialPort serialPort;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyUSB0", // Linux
        "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9" // Windows
    };
    private BufferedReader input;
    private OutputStream output;
    private static final int LENGTH_BUFFER = 14;
    private int counter;
    private int[] receivedDataBuffer;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    public SerialCOM() {
        counter = 0;
        receivedDataBuffer = new int[LENGTH_BUFFER];
    }

    public int[] getReceivedDataBuffer() {
        return receivedDataBuffer;
    }

    public BufferedReader getInput() {
        return input;
    }

    public static int getTIME_OUT() {
        return TIME_OUT;
    }

    public static int getDATA_RATE() {
        return DATA_RATE;
    }

    public void initialize() {
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
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                if (counter < LENGTH_BUFFER) {
                    //System.out.println("CONTADOR = " + counter);
                    if (input.ready()) {
                        receivedDataBuffer[counter] = input.read();
                        counter++;
                    }
                } else {
                    counter = 0;
                    showData();
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    private void showData() {        
        for (int i = 0; i < LENGTH_BUFFER; i++) {
            System.out.println(receivedDataBuffer[i]);
        }
        System.out.println("------- FIM -------\n");
    }
}
