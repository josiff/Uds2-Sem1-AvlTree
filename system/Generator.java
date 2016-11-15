/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import avltree.Node;
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
                        generujIsbn(), generEan(), randomString(CHAR_LENGTH), pob);
            }

        }

        /* Pobocka pb = new Pobocka("Bolesov");
         ct.getPobocky().insert(new Node(pb));
         ct.addKnihu("Trubicka", "a", 0, 1, "ee", pb);
         ct.addKnihu("Azbest", "a", 0, 1, "ee", pb);
         ct.addKnihu("Notebuk", "a", 0, 1, "ee", pb);
         ct.addKnihu("Televizok", "a", 0, 1, "ee", pb);
         ct.addKnihu("Janko", "a", 0, 1, "ee", pb);
         ct.addKnihu("Marienka", "a", 0, 1, "ee", pb);
         ct.addKnihu("Iphone", "a", 0, 1, "ee", pb);
         ct.addKnihu("Guru", "a", 0, 1, "ee", pb);
         ct.addKnihu("Pero", "a", 0, 1, "ee", pb);*/
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

    public static String generujIsbn() {

        int[] d = new int[10];
        int isbn = rndCis(100000000, 999999999);
        d[0] = isbn / 100000000;
        d[1] = (isbn % 100000000) / 10000000;
        d[2] = (isbn % 10000000) / 1000000;
        d[3] = (isbn % 1000000) / 100000;
        d[4] = (isbn % 100000) / 10000;
        d[5] = (isbn % 10000) / 1000;
        d[6] = (isbn % 1000) / 100;
        d[7] = (isbn % 100) / 10;
        d[8] = (isbn % 10);

        for (int i = 0; i < 9; i++) {
            d[9] += d[i] * i + 1;
        }
        d[9] %= 11;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            res.append(d[i]);
        }
        if (d[9] == 10) {
            res.append('X');
        } else {
            res.append(d[9]);
        }
        return res.toString();

    }

    public static int rndCis(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(max - min) + min;

    }

    /**
     * Generovanie ean kodu
     *
     * @return
     */
    public static int generEan() {

       return rndCis(100000000,999999999);

    }

}
