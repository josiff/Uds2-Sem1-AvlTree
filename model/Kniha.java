/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jožko
 */
public class Kniha implements INode {

    public static String NAME = "model.Kniha";

    private String autor;
    private String nazovKnihy;
    private int isbn;
    private int ean;
    private String zaner;
    private Pobocka pobocka;
    private int vypoz; // v dnoch
    private double pokuta;
    private int id;
    private Calendar odda;
    private Calendar doda;

    public Kniha(String nazovKnihy,
            String autor,
            int isbn,
            int ean,
            String zaner,
            int id) {
        this.nazovKnihy = nazovKnihy;
        this.autor = autor;
        this.isbn = isbn;
        this.ean = ean;
        this.zaner = zaner;
        this.id = id;
        this.vypoz = 30;

    }

    public Kniha(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNazovKnihy() {
        return nazovKnihy;
    }

    public void setNazovKnihy(String nazovKnihy) {
        this.nazovKnihy = nazovKnihy;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getEan() {
        return ean;
    }

    public void setEan(int ean) {
        this.ean = ean;
    }

    public String getZaner() {
        return zaner;
    }

    public void setZaner(String zaner) {
        this.zaner = zaner;
    }

    public Pobocka getPobocka() {
        return pobocka;
    }

    public void setPobocka(Pobocka pobocka) {
        this.pobocka = pobocka;
    }

    public int getVypoz() {
        return vypoz;
    }

    public void setVypoz(int vypoz) {
        this.vypoz = vypoz;
    }

    public double getPokuta() {
        return pokuta;
    }

    public void setPokuta(double pokuta) {
        this.pokuta = pokuta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int compare(INode paData) {
        Kniha knih = (Kniha) paData;

        if (this.getId() < knih.getId()) {
            return -1;

        } else if (this.getId() > knih.getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void rezervuj(Calendar calendar) {
        odda = calendar;
        doda = Calendar.getInstance();
        doda.setTime(calendar.getTime());
        doda.add(Calendar.DATE, vypoz);
    }

    @Override
    public String save() {
        return "";
    }

}
