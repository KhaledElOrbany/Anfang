package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;

import java.util.Date;
import java.util.Objects;

import Globals.PrayersCallbacks;
import Utilities.PrayersUtil;

public class PrayersActivity extends AppCompatActivity {
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);
        spinner = (ProgressBar) findViewById(R.id.loadingPanel);
        refresh();
    }

    public void refreshRecords(View view) {
        Snackbar.make(view, "Refreshing.. Please Wait!", Snackbar.LENGTH_LONG)
                .setAction("refresh", refresh()).show();
    }

    @SuppressLint("SetTextI18n")
    private View.OnClickListener refresh() {
        spinner.setVisibility(View.VISIBLE);
        PrayersUtil prayersObj = new PrayersUtil();
        prayersObj.getPrayersDetails(new PrayersCallbacks() {
            @Override
            public void setPrayersDate(DataSnapshot child) {
                TextView prayersDate = findViewById(R.id.txtDate);
                prayersDate.setText("Date: ");
                prayersDate.append(child.getValue(String.class));
                setLastUpdateTime();
            }

            @Override
            public void setPrayersTimes(Iterable<DataSnapshot> prayerTimes) {
                for (DataSnapshot data : prayerTimes) {
                    switch (Objects.requireNonNull(data.getKey())) {
                        case "Imsak":
                            ((TextView) findViewById(R.id.txtImsakTime)).setText(data.getValue(String.class));
                            break;
                        case "Sunrise":
                            ((TextView) findViewById(R.id.txtSunriseTime)).setText(data.getValue(String.class));
                            break;
                        case "Dhuhr":
                            ((TextView) findViewById(R.id.txtDhuhrTime)).setText(data.getValue(String.class));
                            break;
                        case "Asr":
                            ((TextView) findViewById(R.id.txtAsrTime)).setText(data.getValue(String.class));
                            break;
                        case "Maghrib":
                            ((TextView) findViewById(R.id.txtMaghribTime)).setText(data.getValue(String.class));
                            break;
                        case "Isha":
                            ((TextView) findViewById(R.id.txtIshaTime)).setText(data.getValue(String.class));
                            break;
                        default:
                            break;
                    }
                }
                setLastUpdateTime();
            }
        });

        return null;
    }

    @SuppressLint("SetTextI18n")
    private void setLastUpdateTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        TextView lastUpdateTime = findViewById(R.id.txtLastUpdateTime);
        lastUpdateTime.setText("Last Update Time: ");
        lastUpdateTime.append(formatter.format(date));
        spinner.setVisibility(View.GONE);
    }
}