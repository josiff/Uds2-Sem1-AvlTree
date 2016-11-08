/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;
import datechooser.model.DaysGrid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.Setings;
import system.Store;

/**
 *
 * @author Jožko
 */
public class PozKniha implements INode {

    private int id;
    private Calendar odda, doda;
    private Calendar koniec;  //kedy bol skutocny datum vratenia
    private Kniha kniha;
    private int days; //0 ak som vratil ok inak o kolko dni som prekrocil
    private Citatel citatel;

    public PozKniha(int id, Kniha kniha, Calendar aktDatum) {
        this.id = id;
        this.kniha = kniha;
        this.odda = kniha.getOdda();
        this.doda = kniha.getDoda();
        this.koniec = aktDatum;
        this.citatel = kniha.getCitatel();

        if (doda.before(aktDatum)) {
            /* long diff = aktDatum.getTimeInMillis() - doda.getTimeInMillis();
             days = (int) diff / (24 * 60 * 60 * 1000);*/

            days = countDiffDay(doda, aktDatum);

        } else {

            days = 0;
        }

    }

    public PozKniha(String[] atr, Kniha kn, Citatel ct) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            this.id = Integer.parseInt(atr[2]);
            this.kniha = kn;
            this.citatel = ct;

            if (!atr[3].isEmpty()) {

                this.odda = Calendar.getInstance();
                odda.setTime(df.parse(atr[3]));
                this.doda = Calendar.getInstance();
                doda.setTime(df.parse(atr[4]));
                this.koniec = Calendar.getInstance();
                koniec.setTime(df.parse(atr[5]));

            } else {
                this.odda = null;
                this.doda = null;
                this.koniec = null;

            }
            
            this.days = Integer.parseInt(atr[6]);

        } catch (ParseException ex) {
            Logger.getLogger(PozKniha.class.getName()).log(Level.SEVERE, null, ex);
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

    public static int countDiffDay(Calendar cal1, Calendar cal2) {
        int returnInt = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(cal1.getTime());
        c2.setTime(cal2.getTime());

        while (!c1.after(c2)) {
            c1.add(Calendar.DAY_OF_MONTH, 1);
            returnInt++;
        }

        if (returnInt > 0) {
            returnInt = returnInt - 1;
        }

        return (returnInt);
    }

    @Override
    public int compare(INode paData) {
        PozKniha knih = (PozKniha) paData;

        if (this.getId() < knih.getId()) {
            return -1;

        } else if (this.getId() > knih.getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String save(Store store) {
        return kniha.getId() + Setings.DELIMETER
                + citatel.getIdCit() + Setings.DELIMETER
                + id + Setings.DELIMETER
                + store.formatDate(odda) + Setings.DELIMETER
                + store.formatDate(doda) + Setings.DELIMETER
                + store.formatDate(koniec) + Setings.DELIMETER
                + days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Citatel getCitatel() {
        return citatel;
    }

    public void setCitatel(Citatel citatel) {
        this.citatel = citatel;
    }

}
