/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import avltree.INode;
import avltree.Node;
import system.Store;

/**
 *
 * @author Jožko
 */
public class Cislo implements INode {

    private int key;

    public Cislo(int key) {
        super();
        this.key = key;
    }

   

    @Override
    public int compare(INode paNode) {
        
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

    @Override
    public String save(Store st) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    

}
