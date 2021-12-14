package com.el3orb.anfang;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import com.google.firebase.database.DataSnapshot;

import java.util.Objects;

import Globals.Globals;
import Utilities.DialogsUtil;
import Utilities.PlugsUtil;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView plugName = view.findViewById(R.id.plugName);
        Button btnDetails = view.findViewById(R.id.btnOpenPlugDetail);
        Switch stateSwitch = view.findViewById(R.id.switchPlug);

        plugName.setText(name);
        stateSwitch.setChecked(state);

        btnDetails.setOnClickListener(v -> {

        });

        layout.addView(view);
    }

    public void displayPlugDetails(View view) {
        Intent plugDetails = new Intent(MainActivity.this, null);
        startActivity(plugDetails);
    }

    //region Menu
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmPrayers:
                Intent intent = new Intent(MainActivity.this, PrayersActivity.class);
                startActivity(intent);
                return true;
            case R.id.itmWifi:
            case R.id.itmAbout:
                DialogsUtil dialog = new DialogsUtil("Info",
                        "This option will be provided soon..", "Ok");
                dialog.show(getSupportFragmentManager(), "Info");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion
}
