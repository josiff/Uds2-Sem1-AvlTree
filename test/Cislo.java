/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import avltree.Node;

/**
 *
 * @author Jožko
 */
public class Cislo extends Node {

    private int key;

    public Cislo(int key) {
        super();
        this.key = key;
    }

    @Override
    protected void setData(Node paNode) {
        this.key = ((Cislo) paNode).getKey();
    }

    @Override
    public int compare(Node paNode) {
        
        Cislo cis = (Cislo) paNode;
        
        if (this.key < cis.getKey()) {
            return -1;
        } else if (this.key > cis.getKey()) {
            return 1;

        } else {
            return 0;
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.valueOf(this.key);
    }
    
    
    
    
    

}
