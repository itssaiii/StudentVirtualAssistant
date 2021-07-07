package com.mitadt.newui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;

public class StudentTwoFactorAuthentication extends AppCompatActivity {

    EditText StudentSecurity;
    Button Verify;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_two_factor_authentication);
        getSupportActionBar().hide();

        StudentSecurity = findViewById(R.id.StudentSecurityCode);
        Verify = findViewById(R.id.StudentVerifyCode);
        fAuth = FirebaseAuth.getInstance();


        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = StudentSecurity.getText().toString().trim();
                String OriginalCode = "d79opj8k";

                if(code.equals(OriginalCode)){
                    startActivity(new Intent(getApplicationContext(),login_design.class));
                    finish();
                }
                else{
                    Toast.makeText(StudentTwoFactorAuthentication.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}