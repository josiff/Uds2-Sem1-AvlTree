/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;
import java.util.Hashtable;

/**
 *
 * @author Jožko
 */
public class Citatel implements INode {

    private String meno;
    private String przv;
    private int idCit;
    private Hashtable<Integer, Kniha> aktPoz;
    private Hashtable<Integer, Kniha> historia;
    private Hashtable<Integer, Kniha> oneskorene;

    public Citatel() {

    }

    Citatel(int idCit) {
        this.idCit = idCit;
    }

    Citatel(int idCit, String meno, String przv) {
        this.idCit = idCit;
        this.meno = meno;
        this.przv = przv;
    }

    @Override
    public int compare(INode paData) {
        Citatel cit = (Citatel) paData;
        if (this.idCit < cit.getIdCit()) {
            return -1;
        } else if (this.idCit > cit.getIdCit()) {
            return 1;
        } else {
            return 0;

        }
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPrzv() {
        return przv;
    }

    public void setPrzv(String przv) {
        this.przv = przv;
    }

    public int getIdCit() {
        return idCit;
    }

    public void setIdCit(int idCit) {
        this.idCit = idCit;
    }

    public Hashtable<Integer, Kniha> getAktPoz() {
        return aktPoz;
    }

    public void setAktPoz(Hashtable<Integer, Kniha> aktPoz) {
        this.aktPoz = aktPoz;
    }

    public Hashtable<Integer, Kniha> getHistoria() {
        return historia;
    }

    public void setHistoria(Hashtable<Integer, Kniha> historia) {
        this.historia = historia;
    }

    public Hashtable<Integer, Kniha> getOneskorene() {
        return oneskorene;
    }

    public void setOneskorene(Hashtable<Integer, Kniha> oneskorene) {
        this.oneskorene = oneskorene;
    }

}
