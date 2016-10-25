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
public class KnihaInt extends Kniha {

    @Override
    public int compare(INode paData) {

        KnihaInt knih = (KnihaInt) paData;

        if (this.getId() < knih.getId()) {
            return -1;

        } else if (this.getId() > knih.getId()) {
            return 1;
        } else {
            return 0;
        }

    }

}
