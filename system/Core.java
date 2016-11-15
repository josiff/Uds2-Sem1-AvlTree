/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Citatel;
import model.Kniha;
import model.Pobocka;
import model.PozKniha;

/**
 *
 * @author Jožko
 */
public class Core {

    private AvlTree pobocky;
    private AvlTree citatelia;
    private AvlTree knihy;
    //private AvlTree knihyArchiv;  //knihy history + oneskorenia
    private Generator gnr;
    private SimpleDateFormat dateFormat;

    private List<IMessage> imesageListener;
    private Message msg;

    private int pocGenPob, pocGenKnih, pocGenCit;
    private int pocNasledovnikov;

    private double priplatokVratenia = 15;

    private int knihaSeq, citSeq, knihArchSeq;

    public Core() {
        msg = new Message();
        imesageListener = new ArrayList<>();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        pobocky = new AvlTree();
        citatelia = new AvlTree();
        knihy = new AvlTree();
       // knihyArchiv = new AvlTree();
        gnr = new Generator();
        knihaSeq = 1;
        citSeq = 1;
        knihArchSeq = 1;

        pocGenKnih = 10;
        pocGenPob = 5;
        pocGenCit = 10;
        pocNasledovnikov = 5;
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
        if(node == null){
        
            return null;
        }
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
     * Najde citatela podla id cisla
     *
     * @param idCit
     * @return
     */
    public ArrayList findCitatelArray(int idCit) {
        ArrayList list = new ArrayList();
        Citatel cit = findCitatel(idCit);
        if (cit == null) {
            return null;
        }
        list.add(cit);
        return list;
    }

    /**
     * Vymazanie citatela
     *
     * @param idCit
     * @return
     */
    public boolean removeCitatel(int idCit) {

        Citatel cit = findCitatel(idCit);

        if (cit == null) {

            setErrMsg("Čitatel sa nenašiel!");
            return false;

        }

        if (cit.getAktPoz().getCount() > 0) {

            setErrMsg("Čitateľ má požičané knihy. Nemožno ho vymazať!");
            return false;
        }

        //cit.remove(knihyArchiv);

        return citatelia.remove(new Node(cit));
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

        return pob.findKnihuArray(kniha, pocNasledovnikov);

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

        save(getPobocky(), "Pobocky.txt");
        save(getCitatelia(), "Citatelia.txt", "Archiv.txt");
        save(knihy, "Knihy.txt");
        //save(knihyArchiv, "Archiv.txt");

        save(getSaveData(), "Setings.txt");

        setInfoMsg("Dáta boli uložené do súboru");

        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }

    public void save(AvlTree tree, String file) {

        try {
            Store st = new Store();
            if (tree != null) {
                Node root = tree.getRoot();
                if (root != null) {

                    PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));

                    Queue<Node> queue = new LinkedList<>();
                    queue.add(root);
                    while (!queue.isEmpty()) {

                        Node tempNode = queue.poll();
                        // System.out.print(tempNode.getData() + " ");
                        pr.println(tempNode.getData().save(st));

                        if (tempNode.getLeft() != null) {
                            queue.add(tempNode.getLeft());
                        }

                        if (tempNode.getRight() != null) {
                            queue.add(tempNode.getRight());
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

        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }
    
    public void save(AvlTree tree, String file, String file2) {

        try {
            Store st = new Store();
            if (tree != null) {
                Node root = tree.getRoot();
                if (root != null) {

                    PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                    PrintWriter pr2 = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
                    st.setPr(pr2);
                    Queue<Node> queue = new LinkedList<>();
                    queue.add(root);
                    while (!queue.isEmpty()) {

                        Node tempNode = queue.poll();
                        // System.out.print(tempNode.getData() + " ");
                        pr.println(tempNode.getData().save(st));

                        if (tempNode.getLeft() != null) {
                            queue.add(tempNode.getLeft());
                        }

                        if (tempNode.getRight() != null) {
                            queue.add(tempNode.getRight());
                        }
                    }

                    pr.close();
                    pr2.close();

                } else {
                    System.out.println("Chyba");
                }
            } else {
                System.out.println("Chyba");
            }

        } catch (IOException e) {
            System.out.println("Chyba pri zapisovani");
        }

        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }
    
    

    public void save(String data, String file) {

        try {
            PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            pr.println(data);

            pr.close();

        } catch (IOException e) {
            System.out.println("Chyba pri zapisovani");
        }

        //  saveHashTb(getVlastnici(), store, store.getOutV());
    }

    public String getSaveData() {

        return knihaSeq + Setings.DELIMETER + citSeq + Setings.DELIMETER + knihArchSeq;

    }

    public void loadfromTxt() {
        File f;
        BufferedReader bufReader;
        try {

            String regx = "\\|";
            String line = null;
            f = new File("Pobocky.txt");
            if (f.exists()) {
                bufReader = new BufferedReader(new FileReader(f));
                while ((line = bufReader.readLine()) != null) {

                    String[] atr = line.split(regx);

                    addPobocku(atr[0]);
                }
            }

            f = new File("Citatelia.txt");
            if (f.exists()) {
                line = null;
                bufReader = new BufferedReader(new FileReader(f));
                while ((line = bufReader.readLine()) != null) {

                    String[] atr = line.split(regx);

                    citatelia.insert(new Node(new Citatel(atr)));
                }
            }

            line = null;
            f = new File("Knihy.txt");
            if (f.exists()) {
                bufReader = new BufferedReader(new FileReader(f));
                while ((line = bufReader.readLine()) != null) {

                    String[] atr = line.split(regx);

                    Kniha kn = new Kniha(atr);
                    //ak existuje pobocka priradim ju tam
                    if (atr.length > 10) {

                        Pobocka pob = findPobocku(atr[10]);
                        pob.addKnihu(kn);
                        //ak ma vyplneny odda a doda tak hu pozicam
                        if (kn.isPozicana()) {
                            pob.getPozKnihy().insert(new Node(kn.getKnihaStr()));

                            Citatel cit = findCitatel(getInt(atr[11]));
                            cit.urobPozicku(kn);

                        }
                    }
                    knihy.insert(new Node(kn));

                }
            }

            line = null;
            f = new File("Archiv.txt");
            if (f.exists()) {
                bufReader = new BufferedReader(new FileReader(f));
                while ((line = bufReader.readLine()) != null) {

                    String[] atr = line.split(regx);

                    Kniha kn = findKnihuAll(getInt(atr[0]));
                    Citatel ct = findCitatel(getInt(atr[1]));
                    PozKniha pk = new PozKniha(atr, kn, ct);
                    if (pk.getDays() > 0) {
                        // ak som vratil neskoro tak
                        ct.getOneskorenia().add(pk);

                    }
                    ct.getHistoria().add(pk);

                   // knihyArchiv.insert(new Node(pk));

                }
            }

            line = null;
            f = new File("Setings.txt");
            bufReader = new BufferedReader(new FileReader(f));
            while ((line = bufReader.readLine()) != null) {

                String[] atr = line.split(regx);
                knihaSeq = getInt(atr[0]);
                citSeq = getInt(atr[1]);
                knihArchSeq = getInt(atr[2]);

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
    public void urobPozicku(String pobocka, int kniha, int citatel, Calendar calendar) {
        /*treba dorobit osetrenie*/

        if (pobocka.isEmpty() || citatel < 1 || calendar == null) {
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

        if (cit.hasDelay(calendar)) {

            setErrMsg("Čitateľ mešká z vrátením knih");
            return;

        }

        //todo kontrola na meskanie s vratenim
        //musim vytvorit novu instanciu inak by sa to odkazovalo na hlavny cas
        Calendar odda = Calendar.getInstance();
        odda.setTime(calendar.getTime());
        kn.rezervuj(odda);
        kn.setCitatel(cit);
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

    public int getKnihArchSeq() {
        int result = knihArchSeq;
        knihArchSeq++;
        return result;
    }

    public void setKnihArchSeq(int knihArchSeq) {
        this.knihArchSeq = knihArchSeq;
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

    public int getPocNasledovnikov() {
        return pocNasledovnikov;
    }

    public void setPocNasledovnikov(int pocNasledovnikov) {
        this.pocNasledovnikov = pocNasledovnikov;
    }

    public ArrayList getDelayPozKnih(String pobocka, Calendar datum) {

        Pobocka pob = findPobocku(pobocka);
        return pob.getDelayPozKnih(datum);

    }

    /**
     * Vrati knihu zo vsetkych knih
     *
     * @param kniha
     * @return
     */
    public ArrayList findKnihuAllArray(int kniha) {
        ArrayList list = new ArrayList();

        Kniha kn = findKnihuAll(kniha);
        if (kn == null) {
            return null;
        }
        list.add(kn);
        return list;

    }

    /**
     * Vrati knihu zo vsetkych knih
     *
     * @param kniha
     * @return
     */
    public Kniha findKnihuAll(int kniha) {

        Node n = knihy.findNode(new Node(new Kniha(kniha)));
        Kniha kn = (Kniha) n.getData();
        return kn;

    }

    /**
     * Vratenie knihy
     *
     * @param id
     * @param pobocka
     * @param datum
     */
    public void vratKnihu(int id, String pobocka, Calendar datum) {

        Kniha kn = findKnihuAll(id);
        if (kn == null) {
            setErrMsg("Zadaná kniha " + String.valueOf(id) + " sa nenašla!");
            return;
        }

        if (kn.isPozicana() == false) {

            setErrMsg("Zadaná kniha " + String.valueOf(id) + " nie je požičaná!");
            return;
        }

        Pobocka pob = null;
        // ak dam prazdny string tak to beriem ze nemenim pobocku a vraciam povodne
        if (pobocka.isEmpty()) {
            pob = kn.getPobocka();
        } else {
            pob = findPobocku(pobocka);
        }

        if (pob == null) {

            setErrMsg("Zadaná pobočka " + pobocka + " sa nenašla!");
            return;
        }

        double cena = 0;
        PozKniha pozKniha = new PozKniha(getKnihArchSeq(), kn, datum);
        Citatel cit = kn.getCitatel();
        //vymazem citatela
        kn.setCitatel(null);

        cit.vratKnihu(pozKniha);
       // knihyArchiv.insert(new Node(pozKniha));

        cena += kn.getPokuta() * pozKniha.getDays();

        if (pozKniha.getDays() > 60) {

            cit.addBloked(datum);
        }

        kn.setOdda(null);
        kn.setDoda(null);

        kn.getPobocka().vymazPozicku(kn);

        if (pob.equals(kn.getPobocka()) == false) {

            // cena plus ina pobocka
            //nastavim novu pobocku
            //vymazavanie knihy na pobocke  a z celeho zoznamu           
            kn.getPobocka().vymazKnihu(kn);
            pob.addKnihu(kn);
            cena += priplatokVratenia;

        }
        if (cena > 0) {
            setInfoMsg("Kniha bola vratena. Cena vratenia je: " + String.valueOf(cena));
        } else {
            setInfoMsg("Kniha bola vratena");
        }

    }
    
     /**
     * Vratenie knihy
     *
     * @param idCit   
     * @param idKnih   
     * @param pobocka
     * @param datum
     */
    public void vratKnihu(int idCit, int idKnih, String pobocka, Calendar datum) {

        
        Citatel cit = findCitatel(idCit);
        if (cit == null) {
            setErrMsg("Zadaný čitatel " + String.valueOf(idCit) + " sa nenašiel!");
            return;
        }
        
        Kniha kn = (Kniha)cit.getAktPoz().findNode(new Node(new Kniha(idKnih))).getData();

        if(kn == null){
            setErrMsg("Kniha sa nenašla");
            return;
        }
        
        
        if (kn.isPozicana() == false) {

            setErrMsg("Zadaná kniha " + String.valueOf(idKnih) + " nie je požičaná!");
            return;
        }

        Pobocka pob = null;
        // ak dam prazdny string tak to beriem ze nemenim pobocku a vraciam povodne
        if (pobocka.isEmpty()) {
            pob = kn.getPobocka();
        } else {
            pob = findPobocku(pobocka);
        }

        if (pob == null) {

            setErrMsg("Zadaná pobočka " + pobocka + " sa nenašla!");
            return;
        }

        double cena = 0;
        PozKniha pozKniha = new PozKniha(getKnihArchSeq(), kn, datum);
        
        //vymazem citatela
        kn.setCitatel(null);

        cit.vratKnihu(pozKniha);
       // knihyArchiv.insert(new Node(pozKniha));

        cena += kn.getPokuta() * pozKniha.getDays();

        if (pozKniha.getDays() > 60) {

            cit.addBloked(datum);
        }

        kn.setOdda(null);
        kn.setDoda(null);

        kn.getPobocka().vymazPozicku(kn);

        if (pob.equals(kn.getPobocka()) == false) {

            // cena plus ina pobocka
            //nastavim novu pobocku
            //vymazavanie knihy na pobocke  a z celeho zoznamu           
            kn.getPobocka().vymazKnihu(kn);
            pob.addKnihu(kn);
            cena += priplatokVratenia;

        }
        if (cena > 0) {
            setInfoMsg("Kniha bola vratena. Cena vratenia je: " + String.valueOf(cena));
        } else {
            setInfoMsg("Kniha bola vratena");
        }

    }

    /**
     * Vrati historiu ciatela
     *
     * @param citatel
     * @return
     */
    public ArrayList getCitHistry(int citatel) {
        Citatel cit = findCitatel(citatel);

        if (cit == null) {
            setErrMsg("Čitatel " + String.valueOf(citatel) + " sa nenašiel");
            return null;

        }

        return new ArrayList(cit.getHistoria());

    }

    /**
     * Vrati omeskania ciatela
     *
     * @param citatel
     * @return
     */
    public ArrayList getCitOmeskania(int citatel) {
        Citatel cit = findCitatel(citatel);

        if (cit == null) {
            setErrMsg("Čitatel " + String.valueOf(citatel) + " sa nenašiel");
            return null;

        }

        return new ArrayList(cit.getOneskorenia());

    }

    /**
     * Vymazanie knihy
     *
     * @param id
     */
    public void removeKniha(int id) {

        Kniha kn = findKnihuAll(id);

        if (kn == null) {

            setErrMsg("Kniha " + id + " sa nenašla");
            return;
        }

        if (kn.isPozicana()) {

            setErrMsg("Kniha " + id + " je požičaná a preto ju nemožno vymazať");
            return;

        }

        Pobocka pob = kn.getPobocka();
        pob.vymazKnihu(kn);
        //knihy.remove(new Node(kn)); todo

        setInfoMsg("Kniha " + id + " bola vymazaná.");

    }

    /**
     * Vymazanie pobocky
     *
     * @param old
     * @param nova
     */
    public void removePobocka(String old, String nova) {

        Pobocka pobS = findPobocku(old);

        if (pobS == null) {
            setErrMsg("Pobočka " + old + " sa nenašla.");

        }

        Pobocka pobN = findPobocku(nova);

        if (pobN == null) {
            setErrMsg("Pobčka " + nova + " sa nenašla");
        }

        pobN.prevedZ(pobS);
        pobocky.remove(new Node(pobS));

    }

   /* public AvlTree getKihyArchiv() {
        return knihyArchiv;
    }*/

}
