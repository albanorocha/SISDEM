/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class SerialCOM {

    // Private atributes
    private OutputStream serialOut;
    //private InputStream serialIn; // TEST
    private int baudRate;
    private String portaCOM;

    /**
     * SerialCOM constructor
     *
     * @param portaCOM - COM Port ID to send data o arduino
     * @param baud - Baud Rate (In most of cases 9600 value is used)
     */
    public SerialCOM(String portaCOM, int baud) {
        this.portaCOM = portaCOM;
        this.baudRate = baud;
        this.initialize();
    }

    /**
     * Method to verify COM Port communication
     */
    private void initialize() {
        try {
            //Define one variable portId of type CommPortIdentifier to Serial communication
            CommPortIdentifier portId = null;
            try {
                // Try verify if COM Port informed exist
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
            } catch (NoSuchPortException npe) {
                // Catch exception other if COM Port doesn't exist
                JOptionPane.showMessageDialog(null, "COM port was not found.", "COM port", JOptionPane.PLAIN_MESSAGE);
            }
            // Open COM Port
            SerialPort port = (SerialPort) portId.open("Serial Comnunication", this.baudRate);
            serialOut = port.getOutputStream();
            //serialIn = port.getInputStream(); // TEST
            port.setSerialPortParams(this.baudRate, //taxa de transferência da porta serial 
                    SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                    SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                    SerialPort.PARITY_NONE); //receber e enviar dados
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to close Serial COM Port
     */
    public void close() {
        try {
            serialOut.close();
            //serialIn.close(); // TEST
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "It wasn't possible close COM port.", "Close COM port", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void flushSerial() throws IOException {
        try {
            serialOut.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "It wasn't possible flush COM port.", "Open COM port", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * @param data - Value to be sent through COM Port
     */
    public void writeData(int data) {
        try {
            serialOut.write(data);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "It wasn't possible send data. ", "Send Data", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Method to read bytes from serial COM port
     *
     * @return
     */
    /*
     public int readData() {
     try {
     return (serialIn.read());
     } catch (IOException ex) {
     JOptionPane.showMessageDialog(null, "It wasn't possible read data.", "Receive Data", JOptionPane.PLAIN_MESSAGE);
     return -1;
     }
     }*/
}
