package no.pk.controller.scraper;

import no.pk.controller.attributter.Lenker;
import no.pk.util.ReaderHjelp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
    /**
     * Skriver ut alle FINN LEDIGE AUDITORIUM OG SEMINARROM KRONSTAD
     * @throws IOException
     */
    public static String hentLedigDagensSeminarogAuditorieRom(String type) throws IOException {
        ReaderHjelp reader = readCSVInternett(Lenker.SEMINAR_AUDITORIUM);
        String melding = "";
        switch (type) {
            case "ledignaa":
                melding = reader.LedigNaa();
                break;
            case "ledige":
                melding = Arrays.toString(new ArrayList[]{reader.getLedigerom()});
                break;
            case "allerom":
                melding = Arrays.toString(new ArrayList[]{reader.getAllerom()});
                break;
            default:
                melding = "Skriv inn en av tre flølgene kommandoer: \"ledignaa\" \"ledige\" \"allerom\"";
        }
        return melding;
    }


}
