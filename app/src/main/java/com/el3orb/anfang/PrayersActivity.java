package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;

import java.util.Date;
import java.util.Objects;

import Utilities.PrayersUtil;

public class PrayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);
    }

    public void refreshRecords(View view) {
        Snackbar.make(view, "Refreshing.. Please Wait!", Snackbar.LENGTH_LONG)
                .setAction("refresh", refresh()).show();
    }

    @SuppressLint("SetTextI18n")
    private View.OnClickListener refresh() {
        PrayersUtil prayersObj = new PrayersUtil();
        prayersObj.getPrayersTime(prayerTimes -> {
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
        });

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        TextView lastUpdateTime = findViewById(R.id.txtLastUpdateTime);
        lastUpdateTime.setText("Last Update Time: ");
        lastUpdateTime.append(formatter.format(date));
        return null;
    }
}