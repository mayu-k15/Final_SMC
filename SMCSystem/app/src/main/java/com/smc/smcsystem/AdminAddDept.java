package com.smc.smcsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminAddDept extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_dept);
        EditText edname,eduid,edpass;
        String name,pass,uid;
        Button btncreatedept;
        DatabaseReference reference;

        edname = findViewById(R.id.register_dept_name);
        eduid = findViewById(R.id.register_dept_userId);
        edpass = findViewById(R.id.register_dept_password);

        btncreatedept = findViewById(R.id.register_button);

        name = edname.getText().toString();
        uid = eduid.getText().toString();
        pass = edpass.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("Department");


        btncreatedept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object, String> hashMap = new HashMap<>();
                hashMap.put("deptName", edname.getText().toString());
                hashMap.put("deptuid", eduid.getText().toString());
                hashMap.put("deptPass", edpass.getText().toString());
                //DepartmentData dpdata =new DepartmentData(name,uid,pass);
                reference.child(eduid.getText().toString()).setValue(hashMap).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminAddDept.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AdminAddDept.this, "Data is Saved", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });



    }
}