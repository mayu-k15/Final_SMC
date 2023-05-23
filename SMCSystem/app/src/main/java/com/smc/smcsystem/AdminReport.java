package com.smc.smcsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class AdminReport extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    TextView txt;
    int totalpostcount = 0, complcnt = 0;
    int comper;
    int rem;
    int d;
    TextView tvtotalcom, tvPython, tvCPP, tvtotalccomcom,tvtotalcompen;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);

        database = FirebaseDatabase.getInstance();
        tvtotalcom = (TextView) findViewById(R.id.tvtotalnoofcom);
        tvtotalccomcom = (TextView) findViewById(R.id.tvtotalnoofcomcomplited);
        tvtotalcompen = (TextView) findViewById(R.id.tvtotalnoofcompending);
        pieChart = findViewById(R.id.piechart);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        ref = database.getReference("Posts");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("status").getValue().toString().equals("Completed")) {
                        complcnt = complcnt + 1;
                    }
                    totalpostcount = totalpostcount + 1;

                }
                Log.d("TAG", "totalpostcount: "+totalpostcount);
                Log.d("TAG", "complcnt: "+complcnt);
                d = (complcnt * 100) / totalpostcount;
                tvPython.setText(Integer.toString(d));
               // txt.setText(Integer.toString(d));
                tvtotalcom.setText(Integer.toString(totalpostcount));
                tvtotalccomcom.setText(Integer.toString(complcnt));
                tvtotalcompen.setText(Integer.toString(totalpostcount-complcnt));

                tvCPP.setText(Integer.toString(100 - d));
                setData(complcnt,(totalpostcount-complcnt));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setData(int complcnt, int i) {
        pieChart.addPieSlice(
                new PieModel(
                        "Completed",
                        complcnt,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pending",
                        i,
                        Color.parseColor("#66BB6A")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

}




