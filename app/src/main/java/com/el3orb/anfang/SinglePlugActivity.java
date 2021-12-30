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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SinglePlugActivity extends AppCompatActivity {
    String url;
    int plugId;
    String plugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_plug);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e(SinglePlugActivity.this.getLocalClassName(), "intent extras is null");
            return;
        }
        this.plugId = extras.getInt("plugId");
        this.plugName = extras.getString("plugName");
        setTitle(plugName);
        url = "http://192.168.1.50:5000/api/nodes/" + plugId;
        fillLineChart();
    }

    private void fillLineChart() {
        LineChart mChart = findViewById(R.id.lineChart);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart.getXAxis().setTextColor(Color.WHITE);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        mChart.getAxisLeft().setTextColor(Color.WHITE);
        mChart.getAxisRight().setEnabled(false);

        mChart.getDescription().setTextColor(Color.WHITE);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 00f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 90f));
        yValues.add(new Entry(4, 60f));
        yValues.add(new Entry(5, 30f));
        yValues.add(new Entry(6, 80f));
        yValues.add(new Entry(7, 60f));

        LineDataSet set = new LineDataSet(yValues, "Ampere");
        set.setFillAlpha(110);
        set.setColor(Color.RED);
        set.setCircleColor(Color.WHITE);
        set.setValueTextColor(Color.BLUE);
        set.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        mChart.setData(new LineData(dataSets));
    }

    //TODO: change node name
    public void editPlugName() {
        Dialog editNameDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.plug_name_edit_dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);
        name.setText(getTitle());
        name.setHint(R.string.plugNewName);

        builder.setView(view);
        builder.setTitle("Edit name");
        builder.setPositiveButton("Ok", (dialog, which) -> {
            try {
                final JSONArray jsonBody = new JSONArray(
                        "[{\"op\":\"replace\", \"path\":\"NodeName\",\"value\":\"" + name.getText().toString() + "\"}]"
                );
                setTitle(name.getText().toString());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PATCH, url, jsonBody, response -> {
                }, error -> Log.e("Error!!", String.valueOf(error)));
                Volley.newRequestQueue(this).add(jsonArrayRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).setNegativeButton("Cancel", (dialog, which) -> { });
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