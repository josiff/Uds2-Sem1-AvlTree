package model.componentes;

import java.text.DateFormat;
import java.util.ArrayList;
import model.Kniha;
import model.KnihaStr;
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

    public final static int id = 0;
    public final static int name = 1;
    public final static int autor = 2;
    public final static int isbn = 3;
    public final static int ean = 4;
    public final static int zaner = 5;
    public final static int odda = 6;
    public final static int doda = 7;

    public KnihaTableModel() {
        super();

    }

    public KnihaTableModel(ArrayList<Pobocka> rows) {
        super(rows);

    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "Id";
            case name:
                return "Názov";
            case autor:
                return "Autor";
            case isbn:
                return "ISBN";
            case ean:
                return "EAN";
            case zaner:
                return "Žáner";
            case odda:
                return "Začiatok";
            case doda:
                return "Koniec";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Kniha knih = null;
        Object o = rows.get(rowIndex);

        if (o.getClass().getName() == Kniha.NAME) {
            knih = (Kniha) o;

        } else {
            KnihaStr kn = (KnihaStr) o;
            knih = kn.getKniha();
        }

        switch (columnIndex) {
            case id:
                return knih.getId();
            case name:
                return knih.getNazovKnihy();
            case autor:
                return knih.getAutor();
            case isbn:
                return knih.getIsbn();
            case ean:
                return knih.getEan();
            case zaner:
                return knih.getZaner();
            case odda:
                return knih.getOdda() == null ? "" : dateFormat.format(knih.getOdda().getTime());
            case doda:
                return knih.getDoda() == null ? "" : dateFormat.format(knih.getDoda().getTime()); 

        }
        return null;

    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

}
