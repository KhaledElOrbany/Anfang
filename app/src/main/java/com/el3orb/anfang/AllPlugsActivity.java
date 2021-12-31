package com.el3orb.anfang;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import Adapters.AllPlugsAdapter;

public class AllPlugsActivity extends AppCompatActivity {
    final String url = "http://192.168.1.50:5000/api/nodes/";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plugs);
        loadData();
    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(AllPlugsActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            AllPlugsAdapter allPlugsAdapter = new AllPlugsAdapter(this, response);
            recyclerView = findViewById(R.id.allPlugsRecyclerView);
            recyclerView.setAdapter(allPlugsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }, error -> Log.e("Error", String.valueOf(error)));
        requestQueue.add(jsonArrayRequest);
    }
}