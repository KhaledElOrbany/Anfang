package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;

import java.util.Objects;

import Globals.Globals;
import Utilities.PlugsUtil;

public class AllPlugsActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plugs);
        loadData();
        layout = findViewById(R.id.allPlugsContainer);
    }

    public void loadData() {
        ProgressDialog progress = Globals.ShowLoadingPanel(this);
        new PlugsUtil().getPlugs(plugs -> {
            for (DataSnapshot plug : plugs) {
                boolean state = false;
                String name = "";
                for (DataSnapshot data : plug.getChildren()) {
                    if (Objects.equals(data.getKey(), "state")) {
                        state = !Objects.requireNonNull(data.getValue()).toString().equals("0");
                    }
                    if (Objects.equals(data.getKey(), "name")) {
                        name = Objects.requireNonNull(data.getValue()).toString();
                    }
                }
                addCard(plug.getKey(), name, state);
            }
            progress.dismiss();
        });
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void addCard(String id, String name, boolean state) {
        @SuppressLint("InflateParams")
        final View view = getLayoutInflater().inflate(R.layout.plug_card, null);

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