package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.Admin.AdminDashboard;
import com.mitadt.newui.Admin.UniversityPortal;
import com.mitadt.newui.FacultyApp.FacultyLogin;

public class LogInOrSignUp extends AppCompatActivity {

    Button RedirectToFaculty, RedirectToLogIn, UniversityLogin;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_or_sign_up);
        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();



        RedirectToLogIn = findViewById(R.id.redirectLog);
        RedirectToFaculty = findViewById(R.id.redirectFaculty);
        UniversityLogin = findViewById(R.id.universityLogin);

        UniversityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TwoFactorAuthentication.class));

            }
        });

        RedirectToFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FacultyTwoFactorAuthentication.class));

            }
        });

        RedirectToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentTwoFactorAuthentication.class));
            }
        });

    }
}