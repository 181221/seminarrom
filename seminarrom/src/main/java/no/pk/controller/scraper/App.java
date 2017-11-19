package no.pk.controller.scraper;

import no.pk.controller.attributter.Lenker;
import no.pk.util.ReaderHjelp;
import java.io.IOException;
import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
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


}
