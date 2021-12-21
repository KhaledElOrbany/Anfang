package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import Utilities.PharmaciesUtil;

public class AllPharmaciesActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pharmacies);
        layout = findViewById(R.id.allPharmacyContainer);
        loadData();
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.refreshPharmacies);
        pullToRefresh.setOnRefreshListener(() -> {
            layout.removeAllViews();
            loadData();
            pullToRefresh.setRefreshing(false);
        });
    }

    private void loadData() {
        HashMap<String, String> pharmacies;
        try {
            pharmacies = new PharmaciesUtil().execute().get();
            for (String item : pharmacies.keySet()) {
                addCard(item, pharmacies.get(item));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addCard(String name, String phone) {
        @SuppressLint("InflateParams") final View view = getLayoutInflater().inflate(R.layout.pharmacy_card, null);
        ((TextView) view.findViewById(R.id.pharmacyName)).setText(name);
        ((TextView) view.findViewById(R.id.pharmacyPhone)).setText(phone);
        layout.addView(view);
    }
}