package Utilities;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PrayersUtil extends AsyncTask<String, Void, String[]> {
    @Override
    protected String[] doInBackground(String... strings) {
        final String url = "https://www.sabah.com.tr/sakarya-namaz-vakitleri";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements row = doc.select("div.vakitler");
            return row.get(0).getElementsByTag("span").toString()
                    .replace("<span>", "").replace("</span>", ";")
                    .split(";");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
