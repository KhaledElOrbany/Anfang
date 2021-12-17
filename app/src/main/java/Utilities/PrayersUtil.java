package Utilities;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PrayersUtil extends AsyncTask<String, Void, String[]> {
    private final ProgressBar spinner;

    public PrayersUtil(ProgressBar spinner) {
        this.spinner = spinner;
    }

    @Override
    protected void onPreExecute() {
        spinner.setVisibility(View.VISIBLE);
    }

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

    @Override
    protected void onPostExecute(String... strings) {
        spinner.setVisibility(View.GONE);
    }
}
