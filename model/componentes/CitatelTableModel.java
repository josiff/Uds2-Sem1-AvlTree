package model.componentes;

import java.util.ArrayList;
import model.Citatel;
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
public class CitatelTableModel extends MyTableModel {

    public final static int id = 0;
    public final static int meno = 1;
    public final static int priz = 2;
    public final static int blocked = 3;

    public CitatelTableModel() {
        super();

    }

    public CitatelTableModel(ArrayList<Pobocka> rows) {
        super(rows);

    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "Id";
            case meno:
                return "Meno";
            case priz:
                return "Priezvisko";
            case blocked:
                return "Dátum blokovania";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Citatel citatel = (Citatel) rows.get(rowIndex);

        switch (columnIndex) {
            case id:
                return citatel.getIdCit();
            case meno:
                return citatel.getMeno();
            case priz:
                return citatel.getPrzv();
            case blocked:
                return citatel.getDateBlocked() == null ? "" : dateFormat.format(citatel.getDateBlocked().getTime());

        }
        return null;

    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

}
