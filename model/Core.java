/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jožko
 */
public class Core {

    private AvlTree pobocky;
    private AvlTree citatelia;
    private AvlTree knihy;

    public Core() {
        pobocky = new AvlTree();
        citatelia = new AvlTree();
        knihy = new AvlTree();
    }

    /**
     * Pridanie pobocky do stromu
     *
     * @param nazov
     * @return
     */
    public boolean addPobocku(String nazov) {
        if (nazov.equals("")) {
            System.out.println("Empty string");
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
        Node node = pobocky.findNode(new Node(new Pobocka(nazov)));
        return (Pobocka) node.getData();

    }

    /**
     * Vymazanie pobocky podla nazvu
     *
     * @param nazov
     * @return
     */
    public boolean delPobocku(String nazov) {

        return pobocky.remove(new Node(new Pobocka(nazov)));

    }

    /**
     * Pridanie citatela
     *
     * @param idCit
     * @param meno
     * @param przv
     * @return
     */
    public boolean addCitatela(int idCit, String meno, String przv) {

        return citatelia.insert(new Node(new Citatel(idCit, meno, przv)));

    }

    /**
     * Najde citatela podla id cisla
     *
     * @param idCit
     * @return
     */
    public Citatel findCitatel(int idCit) {

        Node node = citatelia.findNode(new Node(new Citatel(idCit)));
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
            if (getPobocky()!= null) {
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
                           
                            pr.println(n.getData().toString());
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

        JOptionPane.showMessageDialog(null, "Uložené");

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

        System.out.println("load");

    }
    
     private int getInt(String str) {
        return Integer.parseInt(str);
    }

}
