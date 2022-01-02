package com.el3orb.anfang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import Adapters.AllPlugsAdapter;
import Globals.Globals;

public class AllPlugsActivity extends AppCompatActivity {
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plugs);
        spinner = findViewById(R.id.allPlugsLoadingPanel);
        spinner.setVisibility(View.VISIBLE);
        loadData();
    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(AllPlugsActivity.this);
        String url = Globals.BaseUrl;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            AllPlugsAdapter allPlugsAdapter = new AllPlugsAdapter(this, response);
            RecyclerView recyclerView = findViewById(R.id.allPlugsRecyclerView);
            recyclerView.setAdapter(allPlugsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            spinner.setVisibility(View.GONE);
        }, error -> {
            spinner.setVisibility(View.GONE);
            Log.e("Error", String.valueOf(error));
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Info")
                    .setMessage("The Server Is Down..")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(AllPlugsActivity.this, MainActivity.class);
                        startActivity(intent);
                    });
            alert.create().show();
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void addNewPlug(View view) {
    }
}