package com.smc.smcsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DepartmentLogin extends AppCompatActivity {

    public Button button;
    EditText username,pass;
    TextView forgotpass;
    DatabaseReference reference;
    boolean isAllFieldsChecked = false;
    String uname,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_login);
        button= (Button) findViewById(R.id.Dlogin_button);
        username=(EditText)findViewById(R.id.deptusername);
        pass=(EditText)findViewById(R.id.deptpassword);
        reference = FirebaseDatabase.getInstance().getReference("Department");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    uname = username.getText().toString();
                    password = pass.getText().toString();
                    reference.child(uname).addListenerForSingleValueEvent(listener);

                }
            }
        });

        /*forgotpass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, Adminsendotp.class);
                startActivity(intent);

            }
        });*/
    }
    private boolean CheckAllFields() {
        if (username.length() == 0) {
            username.setError("Username is required");
            return false;
        }

        if (pass.length() == 0) {
            pass.setError("Password is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                String password1 = snapshot.child("deptPass").getValue(String.class);
                if (password1.equals(password)) {
                    Intent intent = new Intent(DepartmentLogin.this, AdminDashboard.class);
                    intent.putExtra("DepartmentUserName",snapshot.child("deptName").getValue(String.class));
                    //intent.putExtra("DeptName",snapshot.child("deptPass").getValue(String.class);)
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(DepartmentLogin.this,error.toString(),Toast.LENGTH_SHORT).show();
        }

    };
}
