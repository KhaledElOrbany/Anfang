package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import Utilities.DialogsUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayAllPlugs(View view) {
        Intent allPlugs = new Intent(MainActivity.this, AllPlugsActivity.class);
        startActivity(allPlugs);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmPrayers:
                Intent intent = new Intent(MainActivity.this, PrayersActivity.class);
                startActivity(intent);
                return true;
            case R.id.itmWifi:
                DialogsUtil dialog = new DialogsUtil("Info",
                        "This option will be provided soon..", "Ok");
                dialog.show(getSupportFragmentManager(), "Info");
                return true;
            case R.id.itmAbout:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion
}
