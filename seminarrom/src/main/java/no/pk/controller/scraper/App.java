package no.pk.controller.scraper;

import no.pk.controller.Scraper;
import no.pk.controller.attributter.Lenker;
import no.pk.util.RomUtil;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
    public static void main(String[] args) throws IOException {
        Scraper scraper = new Scraper();
        scraper.loggInnFeide();
        readCSVInternett(Lenker.ALLESEMINAR);
        String msg = RomUtil.lagMsg();
    }

}
