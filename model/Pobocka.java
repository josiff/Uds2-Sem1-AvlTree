/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.util.ArrayList;

/**
 *
 * @author Jožko
 */
public class Pobocka implements INode {

    private String nazov;
    private AvlTree knihyStr;
    private AvlTree knihyInt;
    private AvlTree pozKnihy;

    public Pobocka(String nazov) {
        this.nazov = nazov;
        this.knihyInt = new AvlTree();
        this.knihyStr = new AvlTree();
        this.pozKnihy = new AvlTree();
    }

    /**
     * Pridanie knihy do stromu
     *
     * @param kniha
     * @return
     */
    public boolean addKnihu(Kniha kniha) {

        knihyInt.insert(new Node(kniha));
        knihyStr.insert(new Node(new KnihaStr(kniha)));

        return true;

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

    @Override
    public String toString() {
        return nazov;
    }

    @Override
    public String save() {
        return nazov;
    }

    /**
     * Vrati knihu podla nazvu
     *
     * @param nazov
     * @return
     */
    public Kniha findKnihu(String nazov) {

        if (nazov.isEmpty()) {
            return null;
        }

        Node node = knihyStr.findNode(new Node(new KnihaStr(nazov)));
        KnihaStr kniha = (KnihaStr) node.getData();
        return kniha.getKniha();
    }

    /**
     * Vrati knihu podla nazvu
     *
     * @param cis
     * @return
     */
    public Kniha findKnihu(int cis) {

        if (cis < 1) {
            return null;
        }

        Node node = knihyInt.findNode(new Node(new Kniha(cis)));
        Kniha kniha = (Kniha) node.getData();
        return kniha;
    }

    /**
     * Vrati hladanu knihu podla cisla
     *
     * @param id
     * @return
     */
    public ArrayList findKnihuArray(int id) {

        ArrayList list = new ArrayList();
        if (id < 1) {
            return null;
        }

        Kniha kn = findKnihu(id);
        if (kn != null) {
            list.add(kn);
        }

        return list;

    }

    /**
     * Nájde knihu podľa nazvu
     * @param nazov
     * @return 
     */
    public ArrayList findKnihuArray(String nazov) {

        ArrayList list = new ArrayList();
        if (nazov.isEmpty()) {
            return null;
        }

        Kniha kn = findKnihu(nazov);
        if (kn != null) {
            list.add(kn);
        }

        return list;

    }

    /**
     * Pozicane knihy
     *
     * @param kniha
     * @return
     */
    public boolean urobPozicku(Kniha kniha) {

        return pozKnihy.insert(new Node(new KnihaStr(kniha)));

    }

    public AvlTree getPozKnihy() {
        return pozKnihy;
    }

}
