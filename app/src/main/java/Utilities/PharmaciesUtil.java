package Utilities;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class PharmaciesUtil extends AsyncTask<String, Void, HashMap<String, String>> {
    HashMap<String, String> pharmacies;

    @Override
    protected HashMap<String, String> doInBackground(String... String) {
        pharmacies = new HashMap<>();
        final String url = "https://www.nobetcieczanebul.com/sakarya-nobetci-eczane";

        try {
            String phone;
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            for (Element row : doc.select("div.col")) {
                String header = row.select("div.card-header").text();
                String body = row.select("div.card-body").text();
                try {
                    phone = body.substring(body.lastIndexOf(":") + 1).replace("Ara", "");
                } catch (Exception ex) {
                    phone = "";
                }
                pharmacies.put(header.trim(), phone.trim());
            }
            return pharmacies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}