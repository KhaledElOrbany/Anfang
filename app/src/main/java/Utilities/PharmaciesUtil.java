package Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class PharmaciesUtil extends AsyncTask<String, Void, HashMap<String, String>> {
    private final Context context;
    HashMap<String, String> pharmacies;
    private ProgressDialog pd;

    public PharmaciesUtil(Context context) {
        this.context = context;
        pharmacies = new HashMap<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Refreshing.. Please Wait!");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
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
                    phone = body.substring(body.lastIndexOf(":") + 1, body.indexOf("Ara"));
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

    @Override
    protected void onPostExecute(HashMap<String, String> result) {
        pd.dismiss();
    }
}