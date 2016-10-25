/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;
import java.util.Date;

/**
 *
 * @author Jožko
 */
public abstract class Kniha implements INode{
    
    private String autor;
    private String nazovKnihy;
    private String isbn;
    private int ean;
    private String zaner;
    private Pobocka pobocka;
    private int vypoz; // v dnoch
    private double pokuta;
    private int id;
    private Date odda;
    private Date doda;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public Date getOdda() {
        return odda;
    }

    public void setOdda(Date odda) {
        this.odda = odda;
    }

    public Date getDoda() {
        return doda;
    }

    public void setDoda(Date doda) {
        this.doda = doda;
    }
    
    
}
