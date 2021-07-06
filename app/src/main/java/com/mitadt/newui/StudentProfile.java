package com.mitadt.newui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentProfile extends AppCompatActivity {

    TextView fullname,editprof,resetpass;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        getSupportActionBar().hide();

        fullname  = findViewById(R.id.fullname);

        //FIRESTORE
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        resetpass = findViewById(R.id.resetpassstudent);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResetPassword.class));
            }
        });







    }
}