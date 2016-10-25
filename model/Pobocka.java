/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.INode;

/**
 *
 * @author Jožko
 */
public class Pobocka implements INode {

    private String nazov;
    private AvlTree knihyStr;
    private AvlTree knihyInt;

    public Pobocka(String nazov) {
        this.nazov = nazov;
        this.knihyInt = new AvlTree();
        this.knihyStr = new AvlTree();
    }

    @Override
    public int compare(INode paNode) {
        Pobocka pob = (Pobocka) paNode;

       return this.nazov.compareTo(pob.getNazov());

    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public AvlTree getKnihyStr() {
        return knihyStr;
    }

    public void setKnihyStr(AvlTree knihyStr) {
        this.knihyStr = knihyStr;
    }

    public AvlTree getKnihyInt() {
        return knihyInt;
    }

    public void setKnihyInt(AvlTree knihyInt) {
        this.knihyInt = knihyInt;
    }

}
