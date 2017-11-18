package no.pk.scraper.util;

import no.pk.scraper.model.Rom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CsvReaderUtil {
    public static void readCSVInternett(String urlen) throws IOException {
        ArrayList<Rom> allerom = new ArrayList<>();
        String URL = "https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ0Q522x60Yy5505YgX6g5Z5252Yg.html";
        if (URL.contains(".html")) {
            URL = URL.replace("html", "csv");
        }
        java.net.URL url = new URL(URL);
        System.out.println(url.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            if (connection.getResponseCode() == 200) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(streamReader);
                String line = br.readLine();
                line = br.readLine() + br.readLine() + br.readLine();
                String[] fieldsene = line.split(",");
                ReaderHjelp reader = new ReaderHjelp();
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    fieldsene = line.split(",");
                    reader.setOppData(fieldsene);

                }
                br.close();
            } else {
                System.out.println("feil");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

    }
}
