/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import avltree.AvlTree;
import avltree.INode;
import avltree.Node;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;



/**
 *
 * @author Jožko
 */
public class Test {

    private AvlTree tree;
    private ArrayList<INode> items;
    private int pocet, add, remove;
    private int pocOperaci;
    private double parvInsert;

   

    private Random rnd;
    private Random rndOper;

    public Test() {
        this.tree = new AvlTree();
        this.items = new ArrayList<>();
        this.rnd = new Random();
        this.rndOper = new Random();
        

        pocet = 0;

    }

    public void testuj() {

        /* for (int i = 0; i < 10000; i++) {
         insert();

         }
         vypis();

         for (int i = 0; i < 5000; i++) {
         remove();
         }

         vypis();*/
        for (int i = 0; i < 0; i++) {
            insert();

        }
        //vypis();

        double cis = 0.0;

        for (int i = 0; i < getPocOperaci(); i++) {
            cis = rndOper.nextDouble();
            if (cis < getParvInsert()) {

                remove();
                //remove++;
            } else {
                insert();
                //add++;
            }
        }
       
        // vypis();
    }

    private void insert() {
        int value = rnd.nextInt();
        insert(value);

    }

    private void insert(int value) {
        INode cis = new Cislo(value);
        if (tree.insert(new Node(cis))) {
            pocet++;
            items.add(cis);
            add++;
        }

    }

    private void remove() {
        if (pocet > 0) {
            INode cis = items.get(rnd.nextInt(items.size()));
            remove((Cislo) cis);
            remove++;
        }

    }

    private void remove(int value) {
        Cislo cis = new Cislo(value);
        remove(cis);

    }

    private void remove(Cislo cis) {

        if (tree.remove(new Node(cis))) {
            pocet--;
            items.remove(cis);

        } else {

            /*Exception exception = new Exception("Chyba mazania test");
             exception.printStackTrace();*/
        }

    }

    public void vypis() {

        System.out.println(result());

    }

    public String result() {

        String result = "";

        result += "----------Vysledok--------------";
        result += "\nStrom pocet:" + String.valueOf(tree.getCount());
        result += "\nList pocet:" + String.valueOf(items.size());
        result += "\nTest pocet:" + String.valueOf(pocet);

        result += "\nInorder pocet:" + String.valueOf(writeTree(tree.getRoot()));
        result += "\nPridanych:" + String.valueOf(add);
        result += "\nVymazanych:" + String.valueOf(remove);
        result += "\n" + String.valueOf(checkHeight(tree.getRoot()));
        result += "\n---------------------";

        return result;
    }

    /**
     * inorder vypis
     *
     * @param root
     */
    public int writeTree(Node root) {
        if (root == null) {
            return 0;
        }

        int count = 0;

        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                Node n = stack.pop();
                count++;
                // System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }

        return count;

    }

    /*Kontrola vyvazovacich faktorov*/
    private String checkHeight(Node paNode) {

        if (paNode != null) {
            int diff = tree.leftHeight(paNode) - tree.rightHeight(paNode);

            if (diff > 1 || diff < -1) {
                return "Zla vyska";

            } else {
                checkHeight(paNode.getLeft());
                checkHeight(paNode.getRight());
            }
        }

        return "Správna výška";

    }

    public int getPocOperaci() {
        return pocOperaci;
    }

    public void setPocOperaci(int pocOperaci) {
        this.pocOperaci = pocOperaci;
    }

    public double getParvInsert() {
        return parvInsert;
    }

    public void setParvInsert(double parvInsert) {
        this.parvInsert = parvInsert;
    }

  

}
