/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import avltree.AvlTree;
import avltree.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Citatel;
import model.Kniha;
import model.Pobocka;

/**
 *
 * @author Jožko
 */
public class Core {

    private AvlTree pobocky;
    private AvlTree citatelia;
    private AvlTree knihy;
    private Generator gnr;
    private SimpleDateFormat dateFormat;

    private List<IMessage> imesageListener;
    private Message msg;

    private int pocGenPob, pocGenKnih, pocGenCit;

    private int knihaSeq, citSeq;

    public Core() {
        msg = new Message();
        imesageListener = new ArrayList<>();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        pobocky = new AvlTree();
        citatelia = new AvlTree();
        knihy = new AvlTree();
        gnr = new Generator();
        knihaSeq = 1;
        citSeq = 1;

        pocGenKnih = 10;
        pocGenPob = 5;
        pocGenCit = 10;
    }

    public void addMsgListener(IMessage imsg) {
        imesageListener.add(imsg);
    }

    /**
     * Vyvolanie eventu
     *
     * @param msg
     */
    public void showMsg(Message msg) {

        for (IMessage item : imesageListener) {
            item.onMessage(msg);
        }

    }

    /**
     * Pridanie pobocky do stromu
     *
     * @param nazov
     * @return
     */
    public boolean addPobocku(String nazov) {
        if (nazov.equals("")) {
            setErrMsg("Nevyplnili názov pobočky!");
            return false;
        }

        return pobocky.insert(new Node(new Pobocka(nazov)));

    }

    /**
     * Hladanie pobocky podla nazvu
     *
     * @param nazov
     * @return
     */
    public Pobocka findPobocku(String nazov) {
        if (nazov.isEmpty()) {
            setErrMsg("Nevyplnili ste názov pobočky");
            return null;
        }
        Node node = pobocky.findNode(new Node(new Pobocka(nazov)));
        return (Pobocka) node.getData();

    }

    /**
     * Vratenie pobocky v liste
     *
     * @param nazov
     * @return
     */
    public ArrayList findPobockuArray(String nazov) {

        ArrayList list = new ArrayList();
        Pobocka pob = findPobocku(nazov);
        if (pob != null) {
            list.add(pob);
        }
        return list;
    }

    /**
     * Vymazanie pobocky podla nazvu
     *
     * @param nazov
     * @return
     */
    public boolean delPobocku(String nazov) {
        if (nazov.isEmpty()) {
            setErrMsg("Nevyplnili názov pobočky!");
            return false;
        }
        return pobocky.remove(new Node(new Pobocka(nazov)));

    }

    public boolean addKnihu(String nazov, String autor, int isbn, int ean,
            String zaner, String pobocka) {

        return addKnihu(nazov, autor, isbn, ean, zaner, findPobocku(pobocka));

    }

    public boolean addKnihu(String nazov, String autor, int isbn, int ean,
            String zaner, Pobocka pob) {

        if (pob == null) {
            setErrMsg("Pobočka sa nenašla!");

        }

        Kniha kn = new Kniha(nazov, autor, isbn, ean, zaner, getKnihaSeq());
        pob.addKnihu(kn);
        knihy.insert(new Node(kn));

        return true;
    }

    /**
     * Pridanie citatela
     *
     * @param idCit
     * @param meno
     * @param przv
     * @return
     */
    public boolean addCitatela(String meno, String przv) {

        if (meno.isEmpty() || przv.isEmpty()) {
            setErrMsg("Nevyplnili ste meno alebo priezvisko!");
            return false;
        }

        return citatelia.insert(new Node(new Citatel(getCitSeq(), meno, przv)));

    }

    /**
     * Najde citatela podla id cisla
     *
     * @param idCit
     * @return
     */
    public Citatel findCitatel(int idCit) {

        Node node = citatelia.findNode(new Node(new Citatel(idCit)));
        if (node == null) {
            return null;
        }
        return (Citatel) node.getData();
    }

    /**
     * Vymazanie citatela
     *
     * @param idCit
     * @return
     */
    public boolean removeCitatel(int idCit) {

        return citatelia.remove(new Node(new Citatel(idCit)));
    }

    /**
     * Vrati knihy podla stringu
     *
     * @param nazov
     * @return
     */
    public ArrayList getKnihOfPobocka(String nazov) {

        if (nazov.isEmpty()) {
            return null;
        }

        Pobocka pob = findPobocku(nazov);
        if (pob == null) {
            return null;
        }

        return pob.getKnihyStr().getTableRows();

    }

    /**
     * Vrati knihy podla id
     *
     * @param nazov
     * @return
     */
    public ArrayList getKnihOfPobockaInt(String nazov) {

        if (nazov.isEmpty()) {
            return null;
        }

        Pobocka pob = findPobocku(nazov);
        if (pob == null) {
            return null;
        }

        return pob.getKnihyInt().getTableRows();

    }

    /**
     * Hladanie knihy na pobocke
     *
     * @param pobocka
     * @param kniha
     * @return
     */
    public ArrayList findKnihuOfPobocka(String pobocka, String kniha) {

        Pobocka pob = findPobocku(pobocka);
        if (pob == null) {
            setErrMsg("Pobočka sa nenašla");
            return null;
        }

        return pob.findKnihuArray(kniha);

    }

    /**
     * Hladanie knihy na pobocke
     *
     * @param pobocka
     * @param kniha
     * @return
     */
    public ArrayList findKnihuOfPobocka(String pobocka, int kniha) {

        if (pobocka.isEmpty()) {

        }

        Pobocka pob = findPobocku(pobocka);
        if (pob == null) {
            setErrMsg("Pobočka sa nenašla");
            return null;
        }

        return pob.findKnihuArray(kniha);

    }

    public ArrayList getAktPozOfCitatel(int citatel) {

        if (citatel < 1) {
            setErrMsg("Id čitatela nemôže byť záporné");
        }

        Citatel cit = findCitatel(citatel);
        if (cit == null) {
            setErrMsg("Čitatel s id " + String.valueOf(citatel) + " sa nenašiel");
            return null;
        }

        return cit.getAktPoz().getTableRows();

    }

    public AvlTree getPobocky() {
        return pobocky;
    }

    public AvlTree getCitatelia() {
        return citatelia;
    }

    public AvlTree getKnihy() {
        return knihy;
    }

    public void save() {

        try {
            if (getPobocky() != null) {
                Node root = getPobocky().getRoot();
                if (root != null) {

                    PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("Pobocky.txt")));

                    Stack<Node> stack = new Stack<Node>();

                    while (!stack.isEmpty() || root != null) {

                        if (root != null) {
                            stack.push(root);
                            root = root.getLeft();
                        } else {
                            Node n = stack.pop();

                            pr.println(n.getData().save());
                            //System.out.printf("%s, %n", n.toString());
                            root = n.getRight();
                        }

                    }

                    pr.close();

                } else {
                    System.out.println("Chyba");
                }
            } else {
                System.out.println("Chyba");
            }

        } catch (IOException e) {
            System.out.println("Chyba pri zapisovani");
        }

        setInfoMsg("Dáta boli uložené do súboru");

        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }

    public void loadfromTxt() {

        BufferedReader bufReader;
        try {
            bufReader = new BufferedReader(new FileReader("Pobocky.txt"));
            String regx = "\\|";
            String line = null;

            while ((line = bufReader.readLine()) != null) {

                String[] atr = line.split(regx);

                addPobocku(atr[0]);
            }

        } catch (IOException ex) {
            Logger.getLogger(Core.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        setInfoMsg("Dáta boli načítané");

    }

    private int getInt(String str) {
        return Integer.parseInt(str);
    }

    public void generujData() {
        gnr.generujData(this, getPocGenPob(), getPocGenKnih(), getPocGenCit());
        setInfoMsg("Dáta boli vygenerované");

    }

    /**
     * Urobi pozicku
     *
     * @param pobocka
     * @param kniha
     * @param citatel
     * @param calendar
     */
    public void urobPozicku(String pobocka, String kniha, int citatel, Calendar calendar) {
        /*treba dorobit osetrenie*/

        if (pobocka.isEmpty() || kniha.isEmpty() || citatel < 1 || calendar == null) {
            setErrMsg("Nevyplnili ste niektorý z povinných údajov!");
            return;
        }

        Pobocka pob = findPobocku(pobocka);

        if (pob == null) {
            setErrMsg("Pobočka " + pobocka + " sa nenašla!");
            return;
        }

        Kniha kn = pob.findKnihu(kniha);

        if (kn == null) {
            setErrMsg("Kniha " + kniha + " sa nenašla!");
            return;
        }

        /*kontrola ci kniha nie je pozicana*/
        if (kn.getOdda() != null && kn.getDoda() != null) {

            setErrMsg("Kniha " + kniha + " je momentálne požičaná."
                    + "\nPožičať ju bude možné najskôr až " + dateFormat.format(kn.getDoda().getTime()));
            return;
        }

        Citatel cit = findCitatel(citatel);

        if (cit == null) {
            setErrMsg("Čitateľ " + citatel + " sa nenašiel!");
            return;
        }

        /*kontrola ci citatel moze poziciavat*/
        if (cit.isBlocked(calendar)) {
            setErrMsg("Čitateľ " + citatel + " má momentálne blokované požičiavanie!");
            return;
        }
        kn.rezervuj(calendar);
        pob.urobPozicku(kn);
        cit.urobPozicku(kn);

    }

    /**
     * Vrati aktualne pozicane knihy na pobocke
     *
     * @param pobocka
     * @return
     */
    public ArrayList getPobPozKnihy(String pobocka) {

        Pobocka pob = findPobocku(pobocka);
        return pob.getPozKnihy().getTableRows();

    }

    public int getKnihaSeq() {
        int result = knihaSeq;
        knihaSeq++;
        return result;
    }

    public void setKnihaSeq(int knihaSeq) {
        this.knihaSeq = knihaSeq;
    }

    public int getPocGenPob() {
        return pocGenPob;
    }

    public void setPocGenPob(int pocGenPob) {
        this.pocGenPob = pocGenPob;
    }

    public int getPocGenKnih() {
        return pocGenKnih;
    }

    public void setPocGenKnih(int pocGenKnih) {
        this.pocGenKnih = pocGenKnih;
    }

    public int getCitSeq() {
        int result = citSeq;
        citSeq++;
        return result;
    }

    public void setCitSeq(int citSeq) {
        this.citSeq = citSeq;
    }

    public int getPocGenCit() {
        return pocGenCit;
    }

    public void setPocGenCit(int pocGenCit) {
        this.pocGenCit = pocGenCit;
    }

    /**
     * Vyvolanie eventu msg
     *
     * @param text
     */
    private void setErrMsg(String text) {
        msg.viewAsError();
        msg.setMsg(text);
        showMsg(msg);
    }

    /**
     * Vyvolanie eventu msg
     *
     * @param text
     */
    private void setInfoMsg(String text) {
        msg.viewAsInfo();
        msg.setMsg(text);
        showMsg(msg);
    }

}
