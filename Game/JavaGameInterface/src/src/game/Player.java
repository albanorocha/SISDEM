/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.game;

/**
 *
 * @author Eduardo
 */
public class Player {

    private int randomValue, nHits, nErros, nSkip, pushB_confirm, pushB_skip, decimal_value;
    private int score;
    private boolean busy;
    private int[] dSwitch;

    public Player() {
        nErros = nHits = nSkip = pushB_confirm = pushB_skip = decimal_value = 0;
        randomValue = -1; // Invalid value
        dSwitch = new int[4];
        score = 0;
        busy = false;
        System.out.println("Player has been created.");
    }

    public void setBusy(boolean value) {
        busy = value;
    }

    public boolean isBusy() {
        return (busy);
    }

    public int[] getdSwitch() {
        return dSwitch;
    }

    public void setdSwitch(int[] dSwitch) {
        this.dSwitch = dSwitch;
    }

    public int getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(int randomValue) {
        this.randomValue = randomValue;
    }

    public int getDecimal_value() {
        return decimal_value;
    }

    public void setDecimal_value(int decimal_value) {
        this.decimal_value = decimal_value;
    }

    public boolean hasConfirmed() {
        return (pushB_confirm == 1);
    }

    public boolean hasSkipped() {
        return (pushB_skip == 1);
    }

    public void setPushB_confirm(int pushB_confirm) {
        this.pushB_confirm = pushB_confirm;
    }

    public int getPushB_skip() {
        return pushB_skip;
    }

    public void setPushB_skip(int pushB_skip) {
        this.pushB_skip = pushB_skip;
    }

    public int getnHits() {
        return nHits;
    }

    public void setnHits(int nHits) {
        this.nHits = nHits;
    }

    public int getnErros() {
        return nErros;
    }

    public void setnErros(int nErros) {
        this.nErros = nErros;
    }

    public int getnSkip() {
        return nSkip;
    }

    public void setnSkip(int nSkip) {
        this.nSkip = nSkip;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
