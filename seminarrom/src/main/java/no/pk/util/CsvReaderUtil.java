package no.pk.util;

import no.pk.model.Rom;

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
        if (urlen.contains(".html")) {
            urlen = urlen.replace("html", "csv");
        }
        java.net.URL url = new URL(urlen);
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
