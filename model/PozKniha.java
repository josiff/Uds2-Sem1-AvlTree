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

    public PozKniha(Kniha kniha, Calendar aktDatum) {
        this.kniha = kniha;
        this.odda = kniha.getOdda();
        this.doda = kniha.getDoda();
        this.koniec = aktDatum;
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

}
