/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import avltree.AvlTree;
import avltree.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Jožko
 */
public class Test {

    private AvlTree tree;
    private ArrayList<Integer> items;
    private int pocet;

    private Random rnd;

    public Test() {
        this.tree = new AvlTree();
        this.items = new ArrayList<>();
        this.rnd = new Random();
        pocet = 0;

    }

    public void testuj() {

         for (int i = 0; i < 10000; i++) {            
         insert();

         }
         vypis();

         for (int i = 0; i < 8000; i++) {            
         remove();
         }
        
         vypis();
         
        

    }

    private void insert() {
        int value = rnd.nextInt();
        //System.out.println("insert(" + value + ");");
        insert(value);

    }

    private void insert(int value) {
        Cislo cis = new Cislo(value);
        if (tree.insert(cis)) {
            pocet++;
            items.add(value);
        }

    }

    private void remove() {

        //Cislo cis = items.get(rnd.nextInt(items.size()));
        Cislo cis = new Cislo(items.get(rnd.nextInt(pocet)));
        //System.out.println("remove(" + cis.getKey() + ");");
        remove(cis);

    }

    private void remove(int value) {
        Cislo cis = new Cislo(value);
        remove(cis);

    }

    private void remove(Cislo cis) {

        if (tree.remove(cis)) {
            pocet--;
            // items.remove(cis.getKey());

        } else {

            /*Exception exception = new Exception("Chyba mazania test");
             exception.printStackTrace();*/
        }

    }

    public void vypis() {

        System.out.println("----------Vys--------------");
        System.out.println("Strom pocet:" + tree.getCount());
        //System.out.println("List pocet:" + items.size());
        System.out.println("Test pocet:" + pocet);

        /* Collections.sort(items);
         String result = "";
         for (int i : items) {
         result += String.valueOf(i) + ", ";

         }*/
        writeTree(tree.getRoot());
        //System.out.println("\n"+result);
//        System.out.println((tr.checkNext() ? "Spravne " : "Nespravne ") + "rozlozenie prvkov");

        //System.out.println(tr.prechodPre(tr.getRoot()));
        System.out.println("---------------------");
    }

    /**
     * inorder vypis
     *
     * @param root
     */
    public void writeTree(Node root) {
        if (root == null) {
            return;
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

        System.out.println("Inorder pocet:" + count);

    }

}
