package no.pk.controller;


import no.pk.pushbullet.PushbulletClient;
import no.pk.pushbullet.items.device.Device;
import no.pk.util.RomUtil;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

public class PushBullet {
//    private static final String ALLESEMINAR = "https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ0Q522x60Yy5505YgX6g5Z5252Yg.html";
    private static final String ALLESEMINAR = "https://no.timeedit.net/web/hib/db1/student/ri18840446X41YQ0X8Q82016Z011817XXY100Y851Y53431X8Y0YXXX5351Y1Y08431X1XY4YY8400Y0Y1180338XX08980901X8381xYY21Z5g85Z34W5002608X530yQ665Q5006.html";
    public static final String API_KEY = System.getenv("PUSHBULLET_API").toString();

    public static void main(String[] args) throws IOException {
        PushbulletClient client = new PushbulletClient(API_KEY);

        List<Device> devices = client.listDevices();
        for (Device d : devices) {
            System.out.println(d.getNickname() + " \t\t" + d.getIden());
        }

        Scraper scraper = new Scraper();
        scraper.loggInnFeide();
        WebDriver driver = scraper.getDriver();
        driver.navigate().to(ALLESEMINAR);
        readCSVInternett(ALLESEMINAR);

        scraper.avsluttDriver();

        // Skriv melding
       /* String msg = RomUtil.lagMsg();*/
        String body = "LOL";
        String title = "Seminarrom";
        client.sendNotePush(title, body);


        String pederMobil = "40615713";
        String kristofferMobil = "98684488";

//        client.sendSMSPush(pederMobil, body,"com.pushbullet.android", "ujwn9xcUBPwsjAryMKRVgO");
        client.sendSMSPush(kristofferMobil, body,"com.pushbullet.android", "ujwn9xcUBPwsjAryMKRVgO");

    }
}
