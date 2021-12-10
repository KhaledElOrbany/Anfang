package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference();
    DatabaseReference PRAYER_IMSAK;
    DatabaseReference PRAYER_SUNRISE;
    DatabaseReference PRAYER_DHUHR;
    DatabaseReference PRAYER_ASR;
    DatabaseReference PRAYER_MAGHRIB;
    DatabaseReference PRAYER_ISHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.PRAYER_IMSAK = prayerRef.child("/hm01/prayers/Imsak");
        this.PRAYER_SUNRISE = prayerRef.child("/hm01/prayers/Sunrise");
        this.PRAYER_DHUHR = prayerRef.child("/hm01/prayers/Dhuhr");
        this.PRAYER_ASR = prayerRef.child("/hm01/prayers/Asr");
        this.PRAYER_MAGHRIB = prayerRef.child("/hm01/prayers/Maghrib");
        this.PRAYER_ISHA = prayerRef.child("/hm01/prayers/Isha");
    }

    public void refreshRecords(View view) {
        Snackbar.make(view, "Refreshing.. Please Wait!", Snackbar.LENGTH_LONG)
                .setAction("refresh", refresh()).show();
    }

    @SuppressLint("SetTextI18n")
    public View.OnClickListener refresh() {
        PRAYER_IMSAK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtImsakTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Imsak time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        PRAYER_SUNRISE.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtSunriseTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Fajr time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        PRAYER_DHUHR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtDhuhrTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Dhuhr time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        PRAYER_ASR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtAsrTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Asr time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        PRAYER_MAGHRIB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtMaghribTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Maghrib time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        PRAYER_ISHA.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.txtIshaTime)).setText(dataSnapshot.getValue(String.class));
                Log.d("Status", "Isha time is: " + dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Status", "Failed to read value.", error.toException());
            }
        });

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        TextView lastUpdateTime = findViewById(R.id.txtLastUpdateTime);
        lastUpdateTime.setText("Last Update Time: ");
        lastUpdateTime.append(formatter.format(date));
        return null;
    }
}