/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jožko
 */
public class PozKniha {

    private Calendar odda, doda;
    private Calendar koniec;  //kedy bol skutocny datum vratenia
    private Kniha kniha;
    private int days; //0 ak som vratil ok inak o kolko dni som prekrocil

    public PozKniha(Kniha kniha, Calendar aktDatum) {
        this.kniha = kniha;
        this.odda = kniha.getOdda();
        this.doda = kniha.getDoda();
        this.koniec = aktDatum;

        if (doda.before(aktDatum)) {

            long diff = aktDatum.getTimeInMillis() - doda.getTimeInMillis();
            days = (int) diff / (24 * 60 * 60 * 1000);

        } else {

            days = 0;
        }

    }

    public Calendar getOdda() {
        return odda;
    }

    public void setOdda(Calendar odda) {
        this.odda = odda;
    }

    public Calendar getDoda() {
        return doda;
    }

    public void setDoda(Calendar doda) {
        this.doda = doda;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public int getDays() {
        return days;
    }

    public Calendar getKoniec() {
        return koniec;
    }
    
    
    

}
