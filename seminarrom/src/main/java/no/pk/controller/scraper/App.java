package no.pk.controller.scraper;

import no.pk.controller.Scraper;
import no.pk.controller.attributter.Lenker;
import no.pk.model.Rom;
import no.pk.util.CsvReaderUtil;
import no.pk.util.ReaderHjelp;
import no.pk.util.RomUtil;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class App {
    public static void main(String[] args) throws IOException {
        //Scraper scraper = new Scraper();
        //scraper.loggInnFeide();
        //scraper.getDriver().navigate().to("https://no.timeedit.net/web/hib/db1/student/ri18840446X41YQ0X8Q82016Z011817XXY100Y851Y53431X8Y0YXXX5351Y1Y08431X1XY4YY8400Y0Y1180338XX08980901X8381xYY21Z5g85Z34W5002608X530yQ665Q5006.html");
        CsvReaderUtil.headlessReadcsv("asd");
        /*ReaderHjelp reader = readCSVInternett(Lenker.SEMINAR_ROM_TEST);
        scraper.avsluttDriver();
        reader.finnAlleLedige();
        String melding = reader.lagMsg();
        System.out.println(melding);*/
    }

}
