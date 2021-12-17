package Utilities;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PharmaciesUtil extends AsyncTask<Void, Void, Void> {
    private final ProgressBar spinner;

    public PharmaciesUtil(ProgressBar spinner) {
        this.spinner = spinner;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... params) {
        String phone;
        final String url = "https://www.nobetcieczanebul.com/sakarya-nobetci-eczane";

        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            for (Element row : doc.select("div.col")) {
                Elements header = row.select("div.card-header");
                Elements body = row.select("div.card-body");
                phone = body.text().substring(
                        body.text().indexOf("Tel :") + 6, body.text().indexOf("Tel :") + 21);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        spinner.setVisibility(View.GONE);
    }
}