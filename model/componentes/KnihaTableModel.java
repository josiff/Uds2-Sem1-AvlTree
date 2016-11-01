package model.componentes;


import java.util.ArrayList;
import model.Kniha;
import model.Pobocka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jožko
 */
public class KnihaTableModel extends MyTableModel {

    public final static int name = 0;
    
    
   

    public KnihaTableModel(ArrayList<Pobocka> rows) {
        super(rows);
       
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case name:
                return "Názov";


        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Kniha knih = (Kniha) rows.get(rowIndex);

        switch (columnIndex) {
            case name:
                return knih.getNazovKnihy();
          
        }
        return null;

    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

   

    

}
