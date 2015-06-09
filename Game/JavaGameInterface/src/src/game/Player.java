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

    private int nHits, nErros, nSkip, pushB_confirm, pushB_skip, decimal_value;
    private double score;
    private int[] dsw_P1, dsw_P2;

    public Player() {
        nErros = nHits = nSkip = pushB_confirm = pushB_skip = decimal_value = 0;
        dsw_P1 = new int[4];
        dsw_P2 = new int[4];
        score = 0.0;
        System.out.println("Player has been created.");
    }

    public int getPushB_confirm() {
        return pushB_confirm;
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

    public int getDecimal_value_P1() {
        return decimal_value;
    }

    public void setDecimal_value_P1(int decimal_value_P1) {
        this.decimal_value = decimal_value_P1;
    }

    public int[] getDsw_P1() {
        return dsw_P1;
    }

    public void setDsw_P1(int[] dsw_P1) {
        this.dsw_P1 = dsw_P1;
    }

    public int[] getDsw_P2() {
        return dsw_P2;
    }

    public void setDsw_P2(int[] dsw_P2) {
        this.dsw_P2 = dsw_P2;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int calcScore() {
        return ((nHits * 4) - (nSkip * 2) - (nErros * 1));
    }

}
