/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author Jožko
 */
public class Store {

    private SimpleDateFormat dateFormat;

    public Store() {

        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public String formatDate(Calendar cal) {

        return cal == null ? "" : dateFormat.format(cal.getTime());

    }

}
