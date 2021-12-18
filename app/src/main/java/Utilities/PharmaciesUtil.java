package Utilities;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class PharmaciesUtil extends AsyncTask<String, Void, HashMap<String, String>> {
    private final ProgressBar spinner;
    HashMap<String, String> pharmacies;

    public PharmaciesUtil(ProgressBar spinner) {
        this.spinner = spinner;
        pharmacies = new HashMap<>();
    }

    @Override
    protected void onPreExecute() {
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected HashMap<String, String> doInBackground(String... String) {
        String phone;
        final String url = "https://www.nobetcieczanebul.com/sakarya-nobetci-eczane";

        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            for (Element row : doc.select("div.col")) {
                String header = row.select("div.card-header").text();
                String body = row.select("div.card-body").text();
                try {
                    phone = body.substring(body.lastIndexOf(": "), body.indexOf("Ara"));
                } catch (Exception ex) {
                    phone = "";
                }
                pharmacies.put(header, phone);
            }
            return pharmacies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(HashMap<String, String> result) {
        spinner.setVisibility(View.GONE);
    }
}