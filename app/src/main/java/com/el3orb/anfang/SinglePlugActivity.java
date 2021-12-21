package com.el3orb.anfang;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SinglePlugActivity extends AppCompatActivity {
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference(), plug;
    String plugId, plugName;

    private LineChart mChart;

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
        fillLineChart();
    }

    private void fillLineChart() {
        mChart = (LineChart) findViewById(R.id.lineChart);

//        mChart.setOnChartGestureListener(MainActivity.this);
//        mChart.setOnChartValueSelectedListener(MainActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 90f));
        yValues.add(new Entry(4, 60f));
        yValues.add(new Entry(5, 30f));
        yValues.add(new Entry(6, 80f));

        LineDataSet set1 = new LineDataSet(yValues, "Amper");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);
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