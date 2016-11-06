package model.componentes;

import java.text.DateFormat;
import java.util.ArrayList;
import model.Kniha;
import model.KnihaStr;
import model.Pobocka;
import model.PozKniha;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jožko
 */
public class PozKnihaTableModel extends MyTableModel {

    public final static int id = 0;
    public final static int name = 1;
    public final static int odda = 2;
    public final static int doda = 3;
    public final static int koniec = 4;
    public final static int dni = 5;

    public PozKnihaTableModel() {
        super();

    }

    public PozKnihaTableModel(ArrayList<Pobocka> rows) {
        super(rows);

    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "Id";
            case name:
                return "Názov";
            case odda:
                return "Začiatok";
            case doda:
                return "Koniec";
            case koniec:
                return "Vratene";
            case dni:
                return "Pocet dni";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        
        PozKniha kniha  = (PozKniha) rows.get(rowIndex);

     

        switch (columnIndex) {
            case id:
                return kniha.getKniha().getId();
            case name:
                return kniha.getKniha().getNazovKnihy();
            case odda:
                return kniha.getOdda() == null ? "" : dateFormat.format(kniha.getOdda().getTime());
            case doda:
                return kniha.getDoda() == null ? "" : dateFormat.format(kniha.getDoda().getTime());
            case koniec:
                return kniha.getKoniec() == null ? "" : dateFormat.format(kniha.getKoniec().getTime());
            case dni:
                return kniha.getDays();

        }
        return null;

    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

}
