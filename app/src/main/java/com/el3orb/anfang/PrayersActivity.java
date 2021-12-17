package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import Utilities.PrayersUtil;

public class PrayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);
        refresh();
    }

    public void refreshRecords(View view) {
        Snackbar.make(view, "Refreshing.. Please Wait!", Snackbar.LENGTH_LONG)
                .setAction("refresh", refresh()).show();
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    private View.OnClickListener refresh() {
        setTimes();
        setDate();
        setLastUpdateTime();
        return null;
    }

    private void setTimes() {
        String[] prayersTimes = {};
        try {
            prayersTimes = new PrayersUtil(findViewById(R.id.loadingPanel)).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.txtImsakTime)).setText(prayersTimes[0].trim());
        ((TextView) findViewById(R.id.txtSunriseTime)).setText(prayersTimes[1].trim());
        ((TextView) findViewById(R.id.txtDhuhrTime)).setText(prayersTimes[2].trim());
        ((TextView) findViewById(R.id.txtAsrTime)).setText(prayersTimes[3].trim());
        ((TextView) findViewById(R.id.txtMaghribTime)).setText(prayersTimes[4].trim());
        ((TextView) findViewById(R.id.txtIshaTime)).setText(prayersTimes[5].trim());
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    private void setDate() {
        TextView prayersDate = findViewById(R.id.txtDate);
        prayersDate.setText("Date: ");
        prayersDate.append(new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis())));
    }

    @SuppressLint("SetTextI18n")
    private void setLastUpdateTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        TextView lastUpdateTime = findViewById(R.id.txtLastUpdateTime);
        lastUpdateTime.setText("Last Update Time: ");
        lastUpdateTime.append(formatter.format(date));
    }
}