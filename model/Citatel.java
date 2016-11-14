/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.Setings;
import system.Store;

/**
 *
 * @author Jožko
 */
public class Citatel implements INode {

    private String meno;
    private String przv;
    private int idCit;
    private AvlTree aktPoz;

    private Calendar dateBlocked;
    private LinkedList<PozKniha> oneskorenia;
    private LinkedList<PozKniha> historia;

    public Citatel() {

    }

    public Citatel(int idCit) {
        this.idCit = idCit;
    }

    public Citatel(int idCit, String meno, String przv) {
        this.idCit = idCit;
        this.meno = meno;
        this.przv = przv;

        dateBlocked = null;
        aktPoz = new AvlTree();
        oneskorenia = new LinkedList<>();
        historia = new LinkedList<>();
    }

    public Citatel(String[] atr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            this.idCit = Integer.parseInt(atr[0]);
            this.meno = atr[1];
            this.przv = atr[2];

            if (atr.length < 4) {
                dateBlocked = null;
            } else {
                this.dateBlocked = Calendar.getInstance();
                dateBlocked.setTime(df.parse(atr[3]));
            }
            aktPoz = new AvlTree();
            oneskorenia = new LinkedList<>();
            historia = new LinkedList<>();
        } catch (ParseException ex) {
            Logger.getLogger(Citatel.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public AvlTree getAktPoz() {
        return aktPoz;
    }

    @Override
    public String save(Store store) {

        saveHistory(store);
        return idCit + Setings.DELIMETER + meno + Setings.DELIMETER
                + przv + Setings.DELIMETER + store.formatDate(dateBlocked)
                + Setings.DELIMETER;
    }
    
    
    public void saveHistory(Store st) {

        PrintWriter pr = st.getPr();
        for (PozKniha item : historia) {
            pr.println(item.save(st, this));
        }
        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }

    /**
     * Pozicane knihy
     *
     * @param kniha
     * @return
     */
    public boolean urobPozicku(Kniha kniha) {
        kniha.setCitatel(this);
        return aktPoz.insert(new Node(kniha));

    }

    /**
     * Vymazanie knihy
     *
     * @param pozkniha
     * @return
     */
    public void vratKnihu(PozKniha pozkniha) {

        aktPoz.remove(new Node(pozkniha.getKniha()));
        if (pozkniha.getDays() > 0) {
            // ak som vratil neskoro tak
            oneskorenia.add(pozkniha);

        }
        historia.add(pozkniha);

    }

    /**
     * Vrati priznak ci je pouzivatel bloknuty na poziciavanie
     *
     * @param date
     * @return
     */
    public boolean isBlocked(Calendar date) {
        boolean result = false;
        Calendar od = Calendar.getInstance();
        
        if (dateBlocked != null) {
            od.setTime(dateBlocked.getTime());
            od.add(Calendar.YEAR, 1);
            result = od.after(date);
            //ci je blokunuty a ci nahodou nema block na rok
        
        if(!result){
            
            dateBlocked = null;
        }}
        return result;
    }

    /**
     * Datum od kedy je blokovany
     *
     * @return
     */
    public Calendar getDateBlocked() {
        return dateBlocked;
    }

    public void setDateBlocked(Calendar dateBlocked) {
        this.dateBlocked = dateBlocked;
    }

    public LinkedList<PozKniha> getOneskorenia() {
        return oneskorenia;
    }

    public void setOneskorenia(LinkedList<PozKniha> oneskorenia) {
        this.oneskorenia = oneskorenia;
    }

    public LinkedList<PozKniha> getHistoria() {
        return historia;
    }

    public void setHistoria(LinkedList<PozKniha> historia) {
        this.historia = historia;
    }

    /**
     * Vymazanie citatela
     *
     * @param archiv
     */
    public void remove(AvlTree archiv) {

        //vymazem z archivu referencie
      /*  for (PozKniha kniha : oneskorenia) {
            archiv.remove(new Node(kniha));
            kniha.setCitatel(null);
            kniha.setKniha(null);
        }
        //vymazem z archivu referencie
        for (PozKniha kniha : historia) {
            archiv.remove(new Node(kniha));
            kniha.setCitatel(null);
            kniha.setKniha(null);
        }*/

        oneskorenia.clear();
        historia.clear();

    }

    /**
     * Kontrola ci meska
     *
     * @param calendar
     * @return
     */
    public boolean hasDelay(Calendar calendar) {
        Node root = aktPoz.getRoot();
        if (root == null) {
            return false;
        }
        boolean result = false;
        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                Node n = stack.pop();
                Kniha knih = (Kniha) n.getData();
                Calendar doda = knih.getDoda();
                if (doda != null) {

                    if (doda.before(calendar)) {
                        result = true;
                        root = null;
                        stack.clear();
                    }
                }
                //System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }

        return result;
    }

    /**
     * Nastavenie roku blokovania
     *
     * @param calendar
     */
    public void addBloked(Calendar calendar) {

        Calendar end = Calendar.getInstance();
        end.setTime(calendar.getTime());
        end.add(Calendar.YEAR, 1);

        if (dateBlocked == null) {

            dateBlocked = end;

        } else {

            if (dateBlocked.before(end)) {

                dateBlocked = end;

            }

        }

    }

}
