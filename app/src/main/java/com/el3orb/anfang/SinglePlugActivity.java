package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SinglePlugActivity extends AppCompatActivity {
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference();
    DatabaseReference plug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_plug);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e(SinglePlugActivity.this.getLocalClassName(), "intent extras is null");
            return;
        }
        String plugName = extras.getString("plugName");
        setTitle(plugName);
        plug = prayerRef.child("/hm01/plugs" + plugName);
    }

    public void editPlugName(View view) {
    }

    //region Menu
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_plug_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemEditName:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion
}