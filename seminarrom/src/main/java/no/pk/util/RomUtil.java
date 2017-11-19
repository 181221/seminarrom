package no.pk.util;

import no.pk.model.Hendelse;
import no.pk.model.Rom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static no.pk.util.ReaderHjelp.allerom;

public class RomUtil {

    public static ArrayList<String> ledigerom = new ArrayList<>();
    public static ArrayList<Hendelse> ledigehendelser = new ArrayList<>();

    /**
     * Returnerer et rom som er ledig basert på klokken.
     * @return Rom med slutt tid.
     */
    public static String LedigNaa() {
        int naa = hentTime();
        boolean funnet = false;
        String rommet = "Ingen ledige nå";
        for (int i = 0; i < ledigehendelser.size() - 1 && !funnet; i++) {
            Hendelse h = ledigehendelser.get(i);
            Hendelse h1 = ledigehendelser.get(i+1);
            int start = Integer.parseInt(h.getStart().toString().substring(0, 2));
            int slutt = Integer.parseInt(h1.getSlutt().toString().substring(0, 2));
            if(naa >= start  && naa <= slutt ) {
                rommet = "Rom " + h.getRom() + " er ledig til " + slutt;
                funnet = true;
            }
        }
        return rommet;
    }

    public static void printUtRomOgHendelse() {
        for (Rom r : allerom) {
            System.out.print("romnavn " + r.getNavn() + " ");
            for (Hendelse h : r.getHendelser()) {
                System.out.print(h.toString() + ", ");
            }
            System.out.println();
        }
    }

    public static ArrayList<String> finnAlleLedige() {
        String ledige;
        for (int i = 0; i < allerom.size(); i++) {
            Rom r = allerom.get(i);
            int lengde = r.getHendelser().size();
            for (int j = 0; j < lengde - 1; j++) {
                Hendelse h = r.getHendelser().get(j);
                Hendelse h1 = r.getHendelser().get(j + 1);
                if (erLedig(h, h1)) {
                    ledige = "Rom: " + r.getNavn() + " Er ledig fra: " + h.getSlutt() + " til: " + h1.getStart();
                    ledigerom.add(ledige);
                    ledigehendelser.add(h);
                }
            }
        }
        return ledigerom;
    }

    private static int hentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));
    }

    private static boolean erLedig(Hendelse h, Hendelse h1) {
        String slutt = h.getSlutt().substring(0, 2) + h.getSlutt().substring(3, 5);
        String start = h1.getStart().substring(0, 2) + h1.getStart().substring(3, 5);
        int diff = Integer.parseInt(start) - Integer.parseInt(slutt);
        return diff >= 100;
    }

    public static String lagMsg() {
        StringBuilder sb = new StringBuilder();
        for (String s : finnAlleLedige()) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
