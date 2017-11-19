package no.pk.util;
import no.pk.controller.attributter.Lenker;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {
    public static void headlessReadcsv(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Lenker.LOGIN_FEIDE);
        HttpResponse response = null;
        List<NameValuePair> postFields = new ArrayList<NameValuePair>(2);

        postFields.add(new BasicNameValuePair("username", System.getenv("FEIDE_BRUKER")));
        postFields.add(new BasicNameValuePair("password", System.getenv("FEIDE_PW")));
        post.setEntity(new UrlEncodedFormEntity(postFields, HTTP.UTF_8));

        response = client.execute(post);
        String URL = "https://no.timeedit.net/web/hib/db1/student/ri1GZ68Yyga7YYQY5bQ9xmZ1ZQgjnd8cxa0yKYx5YY6a0YgvQy5w655560W66525200.html";
        HttpGet get = new HttpGet(URL);
        response = client.execute(get);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        System.out.println(in.read());
    }

    public static ReaderHjelp readCSVInternett(String urlen) throws IOException {
        if (urlen.contains(".html")) {
            urlen = urlen.replace("html", "csv");
        }



        String tull = "https://no.timeedit.net/web/hib/db1/student/ri18840446X41YQ0X8Q82016Z011817XXY100Y851Y53431X8Y0YXXX5351Y1Y08431X1XY4YY8400Y0Y1180338XX08980901X8381xYY21Z5g85Z34W5012608X531yQ655Q5005.csv";
        java.net.URL url = new URL("https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ2Q652x50Yy5500W606g5Z60.csv");
        System.out.println(url.toString());
        ReaderHjelp reader = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            if (connection.getResponseCode() == 200) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(streamReader);
                String line = br.readLine();
                line = br.readLine() + br.readLine() + br.readLine();
                String[] fieldsene = line.split(",");
                reader = new ReaderHjelp();
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    fieldsene = line.split(",");
                    reader.setOppData(fieldsene);
                }
                br.close();
            } else {
                System.out.println("KOMMER IKKE INN PÅ LENKEN!");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        return reader;
    }
}
