/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.AvlTree;
import avltree.Node;
import test.Cislo;

/**
 *
 * @author Jožko
 */
public class Core {

    private AvlTree pobocky;
    private AvlTree citatelia;
    private AvlTree knihy;

    /**
     * Pridanie pobocky do stromu
     *
     * @param nazov
     * @return
     */
    public boolean addPobocku(String nazov) {
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
     * @param idCit
     * @return 
     */
    public Citatel findCitatel(int idCit) {

        Node node = citatelia.findNode(new Node(new Citatel(idCit)));
        return (Citatel) node.getData();
    }
    
    /**
     * Vymazanie citatela
     * @param idCit
     * @return 
     */
    public boolean removeCitatel(int idCit){
        
        return citatelia.remove(new Node(new Citatel(idCit)));
    }

}
