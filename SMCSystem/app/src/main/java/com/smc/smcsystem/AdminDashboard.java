package com.smc.smcsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboard extends AppCompatActivity
{
    RecyclerView recview;
    AdminAdapterPost adapter;
    TextView report,addDept;
    DatabaseReference reference,ref2;
   private String detan1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Intent intent=getIntent();
        String deptUID=intent.getStringExtra("DepartmentUserName");

        recview=(RecyclerView)findViewById(R.id.adminrecycleview);
        report=findViewById(R.id.reportbtn);


        report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(AdminDashboard.this, AdminReport.class));
                    }
                });

        addDept=findViewById(R.id.adddept);
        addDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this, AdminAddDept.class));
            }
        });

        recview.setLayoutManager(new LinearLayoutManager(this));
//        reference = FirebaseDatabase.getInstance().getReference("Department");
//        ref2  = FirebaseDatabase.getInstance().getReference("Posts");
//
//        reference.child(deptUID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//                  String name = dataSnapshot.child("deptName").getValue(String.class);
//                    detan1=name;
//                    //doSomething();
//                    Log.e("TAG", "onDataChange: "+ name);
//
//                }
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//       DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Department").child(deptUID);
//       ref.child("deptName").get().

       // Log.d("TAG", "gyhy: "+ name);


            FirebaseRecyclerOptions<ModelPost> options =
                    new FirebaseRecyclerOptions.Builder<ModelPost>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("AssignedTo").equalTo(deptUID), ModelPost.class)
                            .build();

            adapter=new AdminAdapterPost(options);
            recview.setAdapter(adapter);





    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}