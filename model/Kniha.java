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
public class Kniha implements INode {

    protected String autor;
    protected String nazovKnihy;
    protected String isbn;
    protected int ean;
    protected String zaner;
    protected Pobocka pobocka;
    protected int vypoz; // v dnoch
    protected double pokuta;
    protected int id;
    protected Date odda;
    protected Date doda;

    public Kniha(String nazovKnihy, int id) {
        this.nazovKnihy = nazovKnihy;
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

    @Override
    public int compare(INode paData) {
        return 0;
    }

   

}
