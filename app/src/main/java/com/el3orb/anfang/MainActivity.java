package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import com.google.firebase.database.DataSnapshot;

import java.util.Objects;

import Utilities.DialogsUtil;
import Utilities.PlugsUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        PlugsUtil plugObj = new PlugsUtil();
        plugObj.getPlugs(plugs -> {
            for (DataSnapshot plug : plugs) {
                ((TextView) findViewById(R.id.plugName)).setText(plug.getKey());
                for (DataSnapshot data : plug.getChildren()) {
                    if (Objects.equals(data.getKey(), "state")) {
                        ((Switch) findViewById(R.id.switchPlug))
                                .setChecked(Boolean.parseBoolean(Objects.requireNonNull(data.getValue()).toString()));
                    }
                }
            }
        });
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