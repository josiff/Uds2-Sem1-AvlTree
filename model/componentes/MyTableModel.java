package model.componentes;


import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jožko
 */
public abstract class MyTableModel implements TableModel {

    protected ArrayList<?> rows;

    public MyTableModel(ArrayList<?> rows) {
        this.rows = rows;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener arg0) {
    }

    @Override
    public void removeTableModelListener(TableModelListener arg0) {
    }

    @Override
    public void setValueAt(Object arg0, int arg1, int arg2) {
    }

    public Object getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public void setRows(ArrayList<?> rows) {
        this.rows = rows;
    }
    
    

}
