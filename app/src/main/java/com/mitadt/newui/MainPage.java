package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
    }

    public void SignOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login_design.class));
        finish();
    }

    public void otpVerify(View view){
        startActivity(new Intent(getApplicationContext(),VerifyOtp.class));
        finish();
    }


}
