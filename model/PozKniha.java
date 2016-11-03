/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Jožko
 */
public class PozKniha {

    private Date odda, doda;
    private Kniha kniha;

    public PozKniha(Kniha kniha, Date odda, Date doda) {
        this.kniha = kniha;
        this.odda = odda;
        this.doda = doda;
    }

    public Date getOdda() {
        return odda;
    }

    public void setOdda(Date odda) {
        this.odda = odda;
    }

    public Date getDoda() {
        return doda;
    }

    public void setDoda(Date doda) {
        this.doda = doda;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }
    
    
    
    
    
    
    
    

}
