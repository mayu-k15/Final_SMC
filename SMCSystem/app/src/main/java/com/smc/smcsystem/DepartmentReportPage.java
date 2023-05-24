package com.smc.smcsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DepartmentReportPage extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    TextView txt;
    int totalpostcount = 0, complcnt = 0;
    int comper;
    int rem;
    int d;
    int totcount=0,completed=0,pending=0;
    TextView tvtotalcom, tvPython, tvCPP, tvtotalccomcom,tvtotalcompen;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_report_page);
        Log.d("TAG", "in dparedterepot: ");

        database = FirebaseDatabase.getInstance();
        tvtotalcom = (TextView) findViewById(R.id.tvtotalnoofcom);
        tvtotalccomcom = (TextView) findViewById(R.id.tvtotalnoofcomcomplited);
        tvtotalcompen = (TextView) findViewById(R.id.tvtotalnoofcompending);
        pieChart = findViewById(R.id.piechart);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        ref = database.getReference("Posts");

        Intent intent=getIntent();
        String deptUID=intent.getStringExtra("DepartmentUserName");

        Query query = ref.orderByChild("AssignedTo").equalTo(deptUID);
        Log.d("TAG", "in dparedterepot11: "+deptUID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("TAG", "in: "+dataSnapshot1.child("title").getValue().toString());
                    totcount +=1;
                    if(dataSnapshot1.child("status").getValue().toString().contains("Completed")){
                        complcnt+=1;
                    }
                }
                Log.d("TAG", "after : "+totcount);
                Log.d("TAG", "after c : "+complcnt);
                Log.d("TAG", "after p : "+(totcount-complcnt));
                tvtotalcom.setText(Integer.toString(totcount));
                tvtotalccomcom.setText(Integer.toString(complcnt));
                tvtotalcompen.setText(Integer.toString(totcount-complcnt));

                setData(complcnt,(totcount-complcnt));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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