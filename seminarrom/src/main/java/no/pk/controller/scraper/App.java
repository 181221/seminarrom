package no.pk.controller.scraper;

import no.pk.controller.attributter.Lenker;
import no.pk.controller.attributter.Verdi;
import no.pk.pushbullet.PushbulletClient;
import no.pk.util.ReaderHjelp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
    public static final String K_API_KEY = System.getenv("K_PUSHBULLET_API").toString();
    public static final String P_API_KEY = System.getenv("P_PUSHBULLET_API").toString();

    /**
     * Skriver ut alle FINN LEDIGE AUDITORIUM OG SEMINARROM KRONSTAD
     * @throws IOException
     */
    public static String hentLedigDagensSeminarogAuditorieRom(String type) throws IOException {
        ReaderHjelp reader = readCSVInternett(Lenker.SEMINAR_AUDITORIUM);
        String melding = "";
        switch (type) {
            case Verdi.LEDIGENAA:
                melding = reader.LedigNaa();
                break;
            case Verdi.LEDIGE:
                melding = Arrays.toString(new ArrayList[]{reader.getLedigerom()});
                break;
            case Verdi.ALLEROM:
                melding = Arrays.toString(new ArrayList[]{reader.getAllerom()});
                break;
            default:
                melding = "Skriv inn en av tre flølgene kommandoer: \"ledignaa\" \"ledige\" \"allerom\"";
        }
        return melding;
    }

    public static void sendViaPushbullet(String melding) {
        PushbulletClient kclient = new PushbulletClient(K_API_KEY);
        PushbulletClient pclient = new PushbulletClient(P_API_KEY);
        String title = "Seminarrom";
        String body = melding;
        kclient.sendNotePush(title, body);
        pclient.sendNotePush(title, body);
    }

}
