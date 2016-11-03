/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import avltree.Node;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Pobocka;

/**
 *
 * @author Jožko
 */
public class Generator {

    private final static String MP = "abcdefghijklmnopqrstuvxyz";
    private final static String VP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rnd;

    private static final int CHAR_LENGTH = 7;

    public Generator() {
        this.rnd = new Random();

    }

    public void generujData(Core ct, int pocPob, int pocKnih, int pocCit) {

        for (int i = 0; i < pocPob; i++) {
            String str = randomString(CHAR_LENGTH);
            // ct.addPobocku(str);
            Pobocka pob = new Pobocka(str);
            ct.getPobocky().insert(new Node(pob));

            for (int j = 0; j < pocKnih; j++) {
                ct.addKnihu(randomString(CHAR_LENGTH), randomString(CHAR_LENGTH),
                        generIsbn(), generIsbn(), randomString(CHAR_LENGTH), pob);
            }

        }

        for (int i = 0; i < pocCit; i++) {
            ct.addCitatela(randomString(CHAR_LENGTH), randomString(CHAR_LENGTH));
        }
    }

    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        sb.append(VP.charAt(rnd.nextInt(VP.length())));
        for (int i = 0; i < len; i++) {
            sb.append(MP.charAt(rnd.nextInt(MP.length())));
        }
        return sb.toString();
    }

    /**
     * Generovanie isbn kodu
     *
     * @return
     */
    public int generIsbn() {

        return 0;

    }

    /**
     * Generovanie ean kodu
     *
     * @return
     */
    public int generEan() {

        return 1;

    }

}
