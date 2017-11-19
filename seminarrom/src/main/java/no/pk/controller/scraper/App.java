package no.pk.controller.scraper;

import no.pk.controller.attributter.Lenker;
import no.pk.pushbullet.PushbulletClient;
import no.pk.util.ReaderHjelp;
import java.io.IOException;
import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
    public static final String API_KEY = System.getenv("PUSHBULLET_API").toString();
    /**
     * Skriver ut alle FINN LEDIGE AUDITORIUM OG SEMINARROM KRONSTAD
     * @throws IOException
     */
    public static String hentDagensSeminarogAuditorieRom() throws IOException {
        ReaderHjelp reader = readCSVInternett(Lenker.SEMINAR_AUDITORIUM);
        reader.finnAlleLedige();
        String melding = reader.lagMsg();
        return melding;
    }

    public static void sendViaPushbullet(String melding) {
        PushbulletClient client = new PushbulletClient(API_KEY);
        String title = "Seminarrom";
        String body = melding;
        client.sendNotePush(title, body);
    }


}
