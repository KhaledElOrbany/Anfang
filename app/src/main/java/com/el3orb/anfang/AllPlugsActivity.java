package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        layout = findViewById(R.id.container);
    }

    public void loadData() {
        ProgressDialog progress = Globals.ShowLoadingPanel(this);
        new PlugsUtil().getPlugs(plugs -> {
            for (DataSnapshot plug : plugs) {

                for (DataSnapshot data : plug.getChildren()) {
                    if (Objects.equals(data.getKey(), "state")) {
                        addCard(plug.getKey(), !Objects.requireNonNull(data.getValue()).toString().equals("0"));
                    }
                }
            }
            progress.dismiss();
        });
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void addCard(String name, boolean state) {
        final View view = getLayoutInflater().inflate(R.layout.plug_card, null);

        TextView plugName = view.findViewById(R.id.plugName);
        Button btnDetails = view.findViewById(R.id.btnOpenPlugDetail);
        Switch stateSwitch = view.findViewById(R.id.switchPlug);

        plugName.setText(name);
        stateSwitch.setChecked(state);

        btnDetails.setOnClickListener(v -> {
            Intent plugDetails = new Intent(AllPlugsActivity.this, null);
            startActivity(plugDetails);
        });

        layout.addView(view);
    }
}