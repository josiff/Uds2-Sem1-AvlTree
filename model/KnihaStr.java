/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;
import system.Store;

/**
 *
 * @author Jožko
 */
public class KnihaStr implements INode {

    private Kniha kniha;
    private String nazov;
    private int id;

    public KnihaStr(String nazov) {
        this.nazov = nazov;
        this.id = -1;
    }

    public KnihaStr(Kniha kniha) {
        this.kniha = kniha;
        this.nazov = kniha.getNazovKnihy();
        this.id = kniha.getId();

    }

    @Override
    public int compare(INode paData) {
        KnihaStr knih = (KnihaStr) paData;

        int cis = this.nazov.compareTo(knih.getNazov());

        if (cis == 0 && id > -1) {

            if (this.id < knih.getKniha().getId()) {

                cis = -1;

            } else if (this.id > knih.getKniha().getId()) {

                cis = 1;
            } else {
                cis = 0;
            }

        }

        return cis;

    }

    @Override
    public String save(Store store) {
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
