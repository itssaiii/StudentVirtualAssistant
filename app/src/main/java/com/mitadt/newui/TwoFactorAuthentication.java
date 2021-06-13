package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mitadt.newui.Admin.UniversityPortal;

public class TwoFactorAuthentication extends AppCompatActivity {

    EditText SecurityCode;
    Button VerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_factor_authentication);
        getSupportActionBar().hide();

        SecurityCode = findViewById(R.id.SecurityCodeAdmin);
        VerifyCode = findViewById(R.id.VerifyCodeAdmin);

        VerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = SecurityCode.getText().toString().trim();
                String OrignalCode = "yjtcr8eak";

                if(code.equals(OrignalCode)){
                    startActivity(new Intent(getApplicationContext(), UniversityPortal.class));
                    finish();
                }
                else{
                    Toast.makeText(TwoFactorAuthentication.this, "Code Is InValid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}