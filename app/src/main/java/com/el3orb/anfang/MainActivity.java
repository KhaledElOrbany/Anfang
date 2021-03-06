package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

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

    public void displayAllPharmacies(View view) {
        Intent allPharmacies = new Intent(MainActivity.this, AllPharmaciesActivity.class);
        startActivity(allPharmacies);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Info")
                        .setMessage("This option will be provided soon..")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> {
                        });
                alert.create().show();
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
