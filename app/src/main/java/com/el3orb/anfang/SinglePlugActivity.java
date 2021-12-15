package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SinglePlugActivity extends AppCompatActivity {
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference(), plug;
    String plugId, plugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_plug);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e(SinglePlugActivity.this.getLocalClassName(), "intent extras is null");
            return;
        }
        this.plugId = extras.getString("plugId");
        this.plugName = extras.getString("plugName");
        setTitle(plugName);
        plug = prayerRef.child("/hm01/plugs/" + plugId + "/");
    }

    public void editPlugName() {
        Dialog editNameDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.plug_name_edit_dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);
        name.setText(getTitle());

        builder.setView(view);
        builder.setTitle("Edit name");
        builder.setPositiveButton("Ok", (dialog, which) -> {
            plug.child("name").setValue(name.getText().toString());
            setTitle(name.getText().toString());
        }).setNegativeButton("Cancel", (dialog, which) -> {

        });
        editNameDialog = builder.create();
        editNameDialog.setCanceledOnTouchOutside(false);
        editNameDialog.show();
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
        if (item.getItemId() == R.id.itemEditName) {
            editPlugName();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
}