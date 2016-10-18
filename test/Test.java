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

    private Random rnd;

    public Test() {
        this.tree = new AvlTree();
        this.items = new ArrayList<>();
        this.rnd = new Random();

    }

    public void testuj() {

        for (int i = 0; i < 10; i++) {
            insert(i);

        }
        vypis();
        for (int i = 0; i < 10; i++) {
            remove(i);
        }
        
       
        
        
        
        System.out.println("----------Vys--------------");
        System.out.println(tree.getCount());
        vypis();
        

    }

    private void insert() {
        int value = rnd.nextInt();
        

    }
    
    private void insert(int value){
        Cislo cis = new Cislo(value);
        tree.insert(cis);
        items.add(value);
    
    }

    private void remove() {

        int i = items.get(rnd.nextInt(items.size()));
        

    }
    
     private void remove(int value) {

       if (tree.remove(new Cislo(value))) {
           // items.remove(value);
        } else {
           Exception exception = new Exception("Chyba mazania test");
           exception.printStackTrace();
        }

    }

    public void vypis() {

       /* Collections.sort(items);
        String result = "";
        for (int i : items) {
            result += String.valueOf(i) + ", ";

        }*/

        writeTree(tree.getRoot());
        //System.out.println("\n"+result);
//        System.out.println((tr.checkNext() ? "Spravne " : "Nespravne ") + "rozlozenie prvkov");
      
        //System.out.println(tr.prechodPre(tr.getRoot()));

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
                System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }
        
        System.out.println(count);

    }

}
