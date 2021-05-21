package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class ForgotPass extends AppCompatActivity {

    EditText mPhoneNumber;
    Button GenerateOtp;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        mPhoneNumber = findViewById(R.id.numberphone);
        GenerateOtp = findViewById(R.id.proceedBtn);

        GenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = mPhoneNumber.getText().toString().trim();

                if(!num.isEmpty()){
                    if (num.length()==10){

                        
                    }
                }


            }
        });
    }
}