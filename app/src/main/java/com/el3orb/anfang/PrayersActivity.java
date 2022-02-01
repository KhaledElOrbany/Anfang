package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

            if (prayersTimes != null) {
                ((TextView) findViewById(R.id.txtImsakTime)).setText(prayersTimes[0].trim());
                ((TextView) findViewById(R.id.txtSunriseTime)).setText(prayersTimes[1].trim());
                ((TextView) findViewById(R.id.txtDhuhrTime)).setText(prayersTimes[2].trim());
                ((TextView) findViewById(R.id.txtAsrTime)).setText(prayersTimes[3].trim());
                ((TextView) findViewById(R.id.txtMaghribTime)).setText(prayersTimes[4].trim());
                ((TextView) findViewById(R.id.txtIshaTime)).setText(prayersTimes[5].trim());
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Info")
                        .setMessage("The Server Is Down..")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> {
                            Intent intent = new Intent(PrayersActivity.this, MainActivity.class);
                            startActivity(intent);
                        });
                alert.create().show();
            }
        } catch (ExecutionException | InterruptedException e) {
            Log.e("Error", "Error in scraping data!", e);
        }
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