/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;

/**
 *
 * @author Jožko
 */
public class KnihaStr implements INode {

    private Kniha kniha;
    private String nazov;

    public KnihaStr(String nazov) {
        this.nazov = nazov;
    }

    public KnihaStr(Kniha kniha) {
        this.kniha = kniha;
        this.nazov = kniha.getNazovKnihy();

    }

    @Override
    public int compare(INode paData) {
        KnihaStr knih = (KnihaStr) paData;

        int cis = this.nazov.compareTo(knih.getNazov());

        return cis;

    }

    @Override
    public String save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

}
