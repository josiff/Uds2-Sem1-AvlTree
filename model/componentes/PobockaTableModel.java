package model.componentes;


import java.util.ArrayList;
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
public class PobockaTableModel extends MyTableModel {

    public final static int name = 0;
    
    
   

    public PobockaTableModel(ArrayList<Pobocka> rows) {
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

        Pobocka pob = (Pobocka) rows.get(rowIndex);

        switch (columnIndex) {
            case name:
                return pob.getNazov();
          
        }
        return null;

    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

   

    

}
