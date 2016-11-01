/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import avltree.INode;

/**
 *
 * @author Jožko
 */
public class KnihaStr extends Kniha{

    public KnihaStr(String nazovKnihy, int id) {
        super(nazovKnihy, id);
    }

    public KnihaStr(Kniha kniha) {
        super(kniha.getNazovKnihy(), kniha.getId());
    }
    
    

    @Override
    public int compare(INode paData) {
        KnihaStr knih = (KnihaStr) paData;
        
        return this.nazovKnihy.compareTo(knih.getNazovKnihy());
        
        
    }
    
}
