/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import system.Store;

/**
 *
 * @author Jožko
 */
public interface INode {
    
    public int compare( INode paData);
    
    public String save(Store store);
    
}
