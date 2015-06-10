/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.game;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.EventQueue;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Constants of the system
     */
    private static final int MAX_DECIMAL_VALUE = 15;
    private static final DateFormat FORMATO = new SimpleDateFormat("mm:ss"); // Format the date type
    static SerialCOM myArduino = new SerialCOM();

    /**
     * Control variables of the system
     */
    private SerialCOM arduinoCommunication;   // Serial communication with ArduinoUNO board
    Random gerador = new Random();
    private int minutes, seconds, remainingTimeSeconds;     // Variables to control counter
    private String counterText;         // Strores counter in String format
    private boolean isPaused;    // Controls counter 
    private Player player1, player2;
    public int[] receivedDataBuffer;

    /**
     * Creates new form MainInterface
     */
    public MainFrame() {
        /**
         * Initialize variables and components of the system.
         */
        initComponents();
        initVariables();
        initInterface();

        /**
         * Starts thread to update counter.
         */
        Thread clockThread = new Thread(new ClockRunnable(), "Clock Thread");
        clockThread.setDaemon(true);
        clockThread.start();

        /**
         * Starts thread to update counter.
         */
        /*
         Thread game = new Thread(new GameIA(), "Game Thread");
         game.setDaemon(true);
         game.start();*/
    }

    /**
     * Method that starts importants variables.
     */
    private void initVariables() {
        // Creanting and connecting Serial COM
        //arduinoCommunication = new SerialCOM();
        //arduinoCommunication.start("COM3", 9600);
        // Creating players
        player1 = new Player();
        player2 = new Player();
        // Seting up auxiliar variables
        minutes = seconds = remainingTimeSeconds = 0;
        counterText = null;
        isPaused = true;
        // Get text that represents time in String format
        getCounterString();
    }

    /**
     *
     */
    private void initInterface() {
        // Palyer 01        
        jLabelStar1Player1.setVisible(false);
        jLabelStar2Player1.setVisible(false);
        jLabelStar3Player1.setVisible(false);
        jLabelStar4Player1.setVisible(false);
        jLabelStar5Player1.setVisible(false);
        jLabelImgPlayer1Right.setVisible(false);
        jLabelImgPlayer1Wrong.setVisible(false);
        // Player 02
        jLabelStar1Player2.setVisible(false);
        jLabelStar2Player2.setVisible(false);
        jLabelStar3Player2.setVisible(false);
        jLabelStar4Player2.setVisible(false);
        jLabelStar5Player2.setVisible(false);
        jLabelImgPlayer2Right.setVisible(false);
        jLabelImgPlayer2Wrong.setVisible(false);

        // Actions
        jButtonStart.setEnabled(false);
        jButtonReset.setEnabled(false);
        jToggleButtonPause.setEnabled(false);
    }

    /**
     * Generate two random values for Players and set on interface
     */
    /*
     private static void generateRandonNumbers() {
     // Player 01
     player1.setRandomValue(gerador.nextInt(MAX_DECIMAL_VALUE));
     jTextFieldDecimalRandom1.setText(String.valueOf(player1.getRandomValue()));
     // Player 02
     player2.setRandomValue(gerador.nextInt(MAX_DECIMAL_VALUE));
     jTextFieldDecimalRandom2.setText(String.valueOf(player2.getRandomValue()));
     }*/
    /**
     *
     */
    /*
     private static void setBinaresRandom() {
     // Set binares random values
     jTextFieldRandomBinaryP1.setText(Integer.toBinaryString(player1.getRandomValue()));
     jTextFieldRandomBinaryP2.setText(Integer.toBinaryString(player2.getRandomValue()));
     }*/
    /**
     *
     */
    /*
     private void setPlayersValues() {
     if (isDataBufferReady()) {
     //if (player1.hasConfirmed()) {
     jTextFieldDecimalPlayer1.setText(String.valueOf(player1.getDecimal_value()));
     jTextFieldDecimalPlayer1.setText(String.valueOf(player2.getDecimal_value()));
     //}
     //if (player2.hasConfirmed()) {
     jTextFieldRandomBinaryP1.setText(Integer.toBinaryString(player1.getDecimal_value()));
     jTextFieldRandomBinaryP2.setText(Integer.toBinaryString(player2.getDecimal_value()));
     //}
     }
     }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldScorePlayer1 = new javax.swing.JTextField();
        jButtonStart = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jToggleButtonPause = new javax.swing.JToggleButton();
        jButtonReset = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldScorePlayer2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerMinutes = new javax.swing.JSpinner();
        jSpinnerSeconds = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldRemainigTime = new javax.swing.JTextField();
        jButtonSetCounter = new javax.swing.JButton();
        jTextFieldDecimalPlayer2 = new javax.swing.JTextField();
        jTextFieldDecimalRandom1 = new javax.swing.JTextField();
        jTextFieldDecimalPlayer1 = new javax.swing.JTextField();
        jTextFieldDecimalRandom2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldBinaryP1 = new javax.swing.JTextField();
        jTextFieldBinaryP2 = new javax.swing.JTextField();
        jTextFieldRandomBinaryP1 = new javax.swing.JTextField();
        jTextFieldRandomBinaryP2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabelImgPlayer1Wrong = new javax.swing.JLabel();
        jLabelImgPlayer1Right = new javax.swing.JLabel();
        jLabelImgPlayer2Right = new javax.swing.JLabel();
        jLabelImgPlayer2Wrong = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabelStar5Player1 = new javax.swing.JLabel();
        jLabelStar1Player1 = new javax.swing.JLabel();
        jLabelStar2Player1 = new javax.swing.JLabel();
        jLabelStar3Player1 = new javax.swing.JLabel();
        jLabelStar4Player1 = new javax.swing.JLabel();
        jLabelStar5Player2 = new javax.swing.JLabel();
        jLabelStar1Player2 = new javax.swing.JLabel();
        jLabelStar2Player2 = new javax.swing.JLabel();
        jLabelStar3Player2 = new javax.swing.JLabel();
        jLabelStar4Player2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISDEM GAME - Convert In Time Game");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 20)); // NOI18N
        jLabel1.setText("JOGADOR 01");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 20)); // NOI18N
        jLabel2.setText("JOGADOR 02");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        jLabel3.setText("PONTUAÇÃO : ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jTextFieldScorePlayer1.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        jTextFieldScorePlayer1.setText("0");
        jTextFieldScorePlayer1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(jTextFieldScorePlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 200, 70));

        jButtonStart.setText("INICIAR");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 590, 220, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setText("TEMPO RESTANTE");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 310, 60));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("AÇÕES");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 550, -1, -1));

        jToggleButtonPause.setText("PAUSAR / CONTINUAR");
        jToggleButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPauseActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonPause, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 590, 160, 100));

        jButtonReset.setText("REINICIAR");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 650, 220, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("DURAÇÃO DA PARTIDA");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 220, 30));

        jLabel10.setText("VALOR INFORMADO (JOGADOR)");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 200, 20));

        jLabel11.setText("VALOR DECIMAL (ALEATÓRIO)");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 190, 20));

        jLabel12.setText("PONTUAÇÃO :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, -1, -1));

        jTextFieldScorePlayer2.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        jTextFieldScorePlayer2.setText("0");
        getContentPane().add(jTextFieldScorePlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 70, 200, 70));

        jLabel14.setText("VALOR INFORMADO (JOGADOR)");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 380, 190, 20));

        jLabel15.setText("VALOR DECIMAL (ALEATÓRIO)");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 250, 190, 20));

        jSpinnerMinutes.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jSpinnerMinutes.setToolTipText("");
        jSpinnerMinutes.setName(""); // NOI18N
        jSpinnerMinutes.setValue(2);
        getContentPane().add(jSpinnerMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 80, 70));

        jSpinnerSeconds.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jSpinnerSeconds.setValue(30);
        getContentPane().add(jSpinnerSeconds, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 80, 70));

        jLabel4.setText("Minutos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 50, -1));

        jLabel6.setText("Segundos");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 590, 60, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel16.setText(":");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 630, 20, -1));

        jTextFieldRemainigTime.setEditable(false);
        jTextFieldRemainigTime.setFont(new java.awt.Font("Tahoma", 0, 140)); // NOI18N
        jTextFieldRemainigTime.setText("00:00");
        getContentPane().add(jTextFieldRemainigTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 370, 190));
        jTextFieldRemainigTime.getAccessibleContext().setAccessibleDescription("");

        jButtonSetCounter.setText("DEFINIR");
        jButtonSetCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetCounterActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSetCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 600, 150, 80));

        jTextFieldDecimalPlayer2.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        getContentPane().add(jTextFieldDecimalPlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 400, 120, 100));

        jTextFieldDecimalRandom1.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        jTextFieldDecimalRandom1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(jTextFieldDecimalRandom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 120, 100));

        jTextFieldDecimalPlayer1.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        getContentPane().add(jTextFieldDecimalPlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 120, 100));

        jTextFieldDecimalRandom2.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        getContentPane().add(jTextFieldDecimalRandom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 270, 120, 100));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 1330, 10));

        jTextFieldBinaryP1.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        getContentPane().add(jTextFieldBinaryP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 425, 170, 55));

        jTextFieldBinaryP2.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        getContentPane().add(jTextFieldBinaryP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 420, 170, 55));

        jTextFieldRandomBinaryP1.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        getContentPane().add(jTextFieldRandomBinaryP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 170, 55));

        jTextFieldRandomBinaryP2.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        getContentPane().add(jTextFieldRandomBinaryP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 170, 55));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 120)); // NOI18N
        jLabel5.setText("X");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 70, 130));

        jLabelImgPlayer1Wrong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/wrong.jpg"))); // NOI18N
        jLabelImgPlayer1Wrong.setText("Wrong");
        getContentPane().add(jLabelImgPlayer1Wrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 160, 150));

        jLabelImgPlayer1Right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/right.jpg"))); // NOI18N
        jLabelImgPlayer1Right.setText("Right");
        getContentPane().add(jLabelImgPlayer1Right, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 160, -1));

        jLabelImgPlayer2Right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/right.jpg"))); // NOI18N
        jLabelImgPlayer2Right.setText("Right");
        getContentPane().add(jLabelImgPlayer2Right, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 160, 150));

        jLabelImgPlayer2Wrong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/wrong.jpg"))); // NOI18N
        jLabelImgPlayer2Wrong.setText("Wrong");
        getContentPane().add(jLabelImgPlayer2Wrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 160, 150));

        jLabel13.setText("ESTRELAS:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, -1, -1));

        jLabel17.setText("ESTRELAS:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabelStar5Player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar5Player1.setText("star1");
        getContentPane().add(jLabelStar5Player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 50, 50));

        jLabelStar1Player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar1Player1.setText("star1");
        getContentPane().add(jLabelStar1Player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 50, 50));

        jLabelStar2Player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar2Player1.setText("star1");
        getContentPane().add(jLabelStar2Player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 50, 50));

        jLabelStar3Player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar3Player1.setText("star1");
        getContentPane().add(jLabelStar3Player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 50, 50));

        jLabelStar4Player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar4Player1.setText("star1");
        getContentPane().add(jLabelStar4Player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 50, 50));

        jLabelStar5Player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar5Player2.setText("star1");
        getContentPane().add(jLabelStar5Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 170, 50, 50));

        jLabelStar1Player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar1Player2.setText("star1");
        getContentPane().add(jLabelStar1Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 170, 50, 50));

        jLabelStar2Player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar2Player2.setText("star1");
        getContentPane().add(jLabelStar2Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 170, 50, 50));

        jLabelStar3Player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar3Player2.setText("star1");
        getContentPane().add(jLabelStar3Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 170, 50, 50));

        jLabelStar4Player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/star.png"))); // NOI18N
        jLabelStar4Player2.setText("star1");
        getContentPane().add(jLabelStar4Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 170, 50, 50));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel18.setText("RESULTADO FINAL");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 530, -1, -1));

        getAccessibleContext().setAccessibleName("SISDEM GAME - Convert In Time");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to define match time duration
     *
     * @param evt
     */
    private void jButtonSetCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetCounterActionPerformed
        getMatchDuration();
        try {
            setCouterString(FORMATO.parse(Integer.toString(minutes) + ":" + Integer.toString(seconds)));
            System.out.println("The game duration has been set.");
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButtonSetCounter.setEnabled(false);
        jButtonStart.setEnabled(true);
        jButtonReset.setEnabled(true);
    }//GEN-LAST:event_jButtonSetCounterActionPerformed

    /**
     * Method that stops counter.
     *
     * @param evt
     */
    private void jToggleButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPauseActionPerformed
        if (jToggleButtonPause.isSelected()) {
            isPaused = true;
            System.out.println("System OFF");
        } else {
            isPaused = false;
            System.out.println("System ON");
        }
    }//GEN-LAST:event_jToggleButtonPauseActionPerformed

    /**
     *
     * @return
     */
    private boolean hasTime() {
        return ((minutes + seconds) > 0);
    }

    /**
     * Method that starts system's processing.
     *
     * @param evt
     */
    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        gameProcessing();
        getMatchDuration();
        isPaused = false;
        if (hasTime() && !isPaused) {
//            gameProcessing();
        } else {
            JOptionPane.showMessageDialog(null, "Set the match duration.", "Error", 0);
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    /**
     * TO DO
     * @param evt 
     */
    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed

    }//GEN-LAST:event_jButtonResetActionPerformed

    /**
     *
     */
    private void getCounterString() {
        // Get text from counterText
        counterText = jTextFieldRemainigTime.getText();
    }

    /**
     *
     * @param date
     */
    public void setCouterString(Date date) {
        jTextFieldRemainigTime.setText(FORMATO.format(date));
    }

    /**
     *
     */
    private void getMatchDuration() {
        minutes = Integer.parseInt(jSpinnerMinutes.getValue().toString());
        seconds = Integer.parseInt(jSpinnerSeconds.getValue().toString());
    }

    /**
     *
     */
    private void calcRemaningTime() {
        // Get reamaning total time
        getCounterString();
        // Get reamaning minutes
        int remainingMinutes = Integer.parseInt(counterText.substring(0, 2));
        // Get reamaning seconds
        int remainingSeconds = Integer.parseInt(counterText.substring(3, 5));
        // Calc total reamaning seconds
        remainingTimeSeconds = (remainingMinutes * 60) + remainingSeconds;
        // Decrement 1 second
        if (remainingTimeSeconds > 0) {
            remainingTimeSeconds--;
        }
        // Calc reamaning minutes
        remainingMinutes = remainingTimeSeconds / 60;
        // Calc reamaning seconds
        remainingSeconds = remainingTimeSeconds % 60;
        // Making reamaning time in String
        counterText = String.valueOf(remainingMinutes) + ":" + String.valueOf(remainingSeconds);
        try {
            // Set reamaning time in text field
            setCouterString(FORMATO.parse(counterText));
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    private static void gameProcessing() {
        System.out.println("The game is running...");

        if (myArduino.getBuffer()[2] == 1) {
            if (myArduino.getBuffer()[0] == 15) {
                System.out.println("ACERTOUUUUUU");
            } else {
                System.out.println("GANHOU GIROMBA!!!");
            }
        } else {
            System.out.println("Diferente.");
        }

        // Random values to be converted by players
        //generateRandonNumbers();
        //setPlayersValues();
        // Processing data coming from Arduino UNO
        //processingDataBuffer();
        // Update interface
        //isPaused = false;
        //jButtonSetCounter.setEnabled(false);
        //jButtonReset.setEnabled(true);
        //jButtonStart.setEnabled(false);
        //jToggleButtonPause.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        myArduino.start("COM3", 9600);

        Thread listenSerial;
        listenSerial = new Thread() {
            @Override
            public void run() {
                /**
                 * The following line will keep this app alive for 1000 seconds,
                 * waiting for events to occur and responding to them (printing
                 * incoming messages to console).
                 */
                gameProcessing();
            }
        };
        listenSerial.start();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    /**
     * Runnable que contém o código que atuará na nossa thread. Basicamente, ele
     * chama o método setCouterString de segundo em segundo, passando a data
     * atual.
     */
    private class ClockRunnable implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    // Aqui chamamos o setCouterString através da EventQueue da AWT.  
                    // Conforme dito, isso garante Thread safety para o Swing.  
                    EventQueue.invokeLater(() -> {
                        if (!isPaused) {
                            calcRemaningTime();
                        } else {
                            System.out.println("System is paused.");
                        }
                    });
                    // Fazemos nossa thread dormir por um segundo, liberando o  
                    // processador para outras threads processarem.  
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                //
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSetCounter;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImgPlayer1Right;
    private javax.swing.JLabel jLabelImgPlayer1Wrong;
    private javax.swing.JLabel jLabelImgPlayer2Right;
    private javax.swing.JLabel jLabelImgPlayer2Wrong;
    private javax.swing.JLabel jLabelStar1Player1;
    private javax.swing.JLabel jLabelStar1Player2;
    private javax.swing.JLabel jLabelStar2Player1;
    private javax.swing.JLabel jLabelStar2Player2;
    private javax.swing.JLabel jLabelStar3Player1;
    private javax.swing.JLabel jLabelStar3Player2;
    private javax.swing.JLabel jLabelStar4Player1;
    private javax.swing.JLabel jLabelStar4Player2;
    private javax.swing.JLabel jLabelStar5Player1;
    private javax.swing.JLabel jLabelStar5Player2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinnerMinutes;
    private javax.swing.JSpinner jSpinnerSeconds;
    private javax.swing.JTextField jTextFieldBinaryP1;
    private javax.swing.JTextField jTextFieldBinaryP2;
    private javax.swing.JTextField jTextFieldDecimalPlayer1;
    private javax.swing.JTextField jTextFieldDecimalPlayer2;
    private javax.swing.JTextField jTextFieldDecimalRandom1;
    private javax.swing.JTextField jTextFieldDecimalRandom2;
    private javax.swing.JTextField jTextFieldRandomBinaryP1;
    private javax.swing.JTextField jTextFieldRandomBinaryP2;
    private javax.swing.JTextField jTextFieldRemainigTime;
    private javax.swing.JTextField jTextFieldScorePlayer1;
    private javax.swing.JTextField jTextFieldScorePlayer2;
    private javax.swing.JToggleButton jToggleButtonPause;
    // End of variables declaration//GEN-END:variables
}
