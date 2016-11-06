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
import java.util.LinkedList;

/**
 *
 * @author Jožko
 */
public class Citatel implements INode {

    private String meno;
    private String przv;
    private int idCit;
    private AvlTree aktPoz;

    private boolean blocked;
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
        blocked = false;
        dateBlocked = null;
        aktPoz = new AvlTree();
        oneskorenia = new LinkedList<>();
        historia = new LinkedList<>();
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
    public String save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Pozicane knihy
     *
     * @param kniha
     * @return
     */
    public boolean urobPozicku(Kniha kniha) {

        return aktPoz.insert(new Node(kniha));

    }

    /**
     * Vymazanie knihy
     *
     * @param kniha
     * @param datum
     * @return
     */
    public void vratKnihu(Kniha kniha, Calendar datum) {

        PozKniha pozkniha = new PozKniha(kniha, datum);
        aktPoz.remove(new Node(kniha));
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
        boolean result = true;
        Calendar od = dateBlocked;
        if (od != null) {
            od.add(Calendar.YEAR, 1);
            result = od.after(date);
            //ci je blokunuty a ci nahodou nema block na rok
        }
        return blocked && result;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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

    
    
}
