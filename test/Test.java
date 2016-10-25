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
        for (int i = 0; i < 100; i++) {
            insert();

        }
        vypis();

        double cis = 0.0;
        for (int i = 0; i < 10000; i++) {
            cis = rndOper.nextDouble();
            if (cis < 0.5) {

                remove();
                remove++;
            } else {
                insert();
                add++;
            }
        }
        vypis();

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
        }

    }

    private void remove() {
        if (pocet > 0) {
            INode cis = items.get(rnd.nextInt(items.size()));
            remove((Cislo)cis);
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

        System.out.println("----------Vys--------------");
        System.out.println("Strom pocet:" + tree.getCount());
        System.out.println("List pocet:" + items.size());
        System.out.println("Test pocet:" + pocet);

        writeTree(tree.getRoot());
        System.out.println("Pridanych:" + add);
        System.out.println("Vymazanych:" + remove);
        checkHeight(tree.getRoot());
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

    /*Kontrola vyvazovacich faktorov*/
    private void checkHeight(Node paNode) {

        if (paNode != null) {
            int diff = tree.leftHeight(paNode) - tree.rightHeight(paNode);

            if (diff > 1 || diff < -1) {
                System.out.println("Zla vyska");
                return;
            } else {
                checkHeight(paNode.getLeft());
                checkHeight(paNode.getRight());
            }
        }

    }

}
