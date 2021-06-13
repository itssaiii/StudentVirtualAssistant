package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mitadt.newui.FacultyApp.FacultyLogin;

public class FacultyTwoFactorAuthentication extends AppCompatActivity {

    EditText FacultySecurityCode;
    Button VerifyCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_two_factor_authentication);
        getSupportActionBar().hide();

        FacultySecurityCode = findViewById(R.id.FacultySecurityCode);
        VerifyCode = findViewById(R.id.FacultyVerifyCode);

        VerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = FacultySecurityCode.getText().toString().trim();
                String OriginalCode = "fgzxsr7b";
                if(code.equals(OriginalCode)){
                    startActivity(new Intent(getApplicationContext(), FacultyLogin.class));
                }
                else{
                    Toast.makeText(FacultyTwoFactorAuthentication.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}