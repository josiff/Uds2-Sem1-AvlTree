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
import java.util.Calendar;
import java.util.Stack;
import system.Store;

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

        KnihaStr kStr = new KnihaStr(kniha);
        kniha.setKnihaStr(kStr);
        kniha.setPobocka(this);
        knihyInt.insert(new Node(kniha));
        knihyStr.insert(new Node(kStr));

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
    public String save(Store store) {
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
     *
     * @param nazov
     * @param pocet kolko nasledovnikov chcem vidiet
     * @return
     */
    public ArrayList findKnihuArray(String nazov, int pocet) {

        if (nazov.isEmpty()) {
            return null;
        }

        /*Kniha kn = findKnihu(nazov);
         if (kn != null) {
         list.add(kn);
         }*/
        return knihyStr.inOrderMatch(new Node(new KnihaStr(nazov)), pocet);

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

    /**
     * Vymazanie pozicanej knihy
     *
     * @param kniha
     * @return
     */
    public boolean vymazPozicku(Kniha kniha) {

        return pozKnihy.remove(new Node(new KnihaStr(kniha)));

    }

    public AvlTree getPozKnihy() {
        return pozKnihy;
    }

    public ArrayList getDelayPozKnih(Calendar datum) {

        ArrayList<KnihaStr> list = new ArrayList<>();
        Node root = pozKnihy.getRoot();
        if (root == null) {
            return list;
        }

        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                Node n = stack.pop();
                KnihaStr knih = (KnihaStr) n.getData();
                Calendar doda = knih.getKniha().getDoda();
                if (doda != null) {

                    if (doda.before(datum)) {
                        list.add(knih);
                    }
                }
                //System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }
        return list;

    }

    /**
     * Vymazanie knihy
     *
     * @param kniha
     */
    public void vymazKnihu(Kniha kniha) {

        KnihaStr knihaStr = kniha.getKnihaStr();
        kniha.setKnihaStr(null);
        kniha.setPobocka(null);
        knihyInt.remove(new Node(kniha));
        knihaStr.setKniha(null);
        knihyStr.remove(new Node(knihaStr));

    }

    /**
     * Presunutie pobocky
     *
     * @param pobS
     */
    public void prevedZ(Pobocka pobS) {

        Node root = pobS.getKnihyInt().getRoot();
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                Node n = stack.pop();
                Kniha knih = (Kniha) n.getData();

                addKnihu(knih);

                if (knih.isPozicana()) {
                    urobPozicku(knih);
                }

                //System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }

        pobS.setKnihyInt(null);
        pobS.setKnihyStr(null);

    }

}
