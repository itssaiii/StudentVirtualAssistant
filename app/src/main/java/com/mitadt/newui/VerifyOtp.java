package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerifyOtp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();
    }
}