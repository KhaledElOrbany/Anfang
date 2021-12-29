package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import Globals.Parsing;
import Utilities.PlugsUtil;

public class AllPlugsActivity extends AppCompatActivity {
    LinearLayout layout;
    String url = "http://192.168.1.50:5000/api/nodes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plugs);
        loadData();
        layout = findViewById(R.id.allPlugsContainer);
    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(AllPlugsActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObj = response.getJSONObject(i);
                    addCard(
                            jsonObj.getString("nodeName"),
                            jsonObj.getString("nodeName"),
                            Parsing.toBool(jsonObj.getString("nodeState"))
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Log.e("Error", String.valueOf(error)));
        requestQueue.add(jsonArrayRequest);
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void addCard(String id, String name, boolean state) {
        @SuppressLint("InflateParams") final View view = getLayoutInflater().inflate(R.layout.plug_card, null);

        view.setOnClickListener(v -> {
            Intent plugDetails = new Intent(AllPlugsActivity.this, SinglePlugActivity.class);
            plugDetails.putExtra("plugId", id);
            plugDetails.putExtra("plugName", name);
            startActivity(plugDetails);
        });

        ((TextView) view.findViewById(R.id.plugName)).setText(name);

        Switch stateSwitch = view.findViewById(R.id.switchPlug);
        stateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PlugsUtil plugsUtil = new PlugsUtil();
            plugsUtil.setPlugsState(id, isChecked);
        });
        stateSwitch.setChecked(state);
        layout.addView(view);
    }

    public void addNewPlug(View view) {
    }
}