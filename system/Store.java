/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Jožko
 */
public class Store {

    private SimpleDateFormat dateFormat;
    private PrintWriter pr;

    public Store() {

        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public String formatDate(Calendar cal) {

        return cal == null ? "" : dateFormat.format(cal.getTime());

    }

    public PrintWriter getPr() {
        return pr;
    }

    public void setPr(PrintWriter pr) {
        this.pr = pr;
    }
    
    

}
