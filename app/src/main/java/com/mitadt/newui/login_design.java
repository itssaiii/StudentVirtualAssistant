package com.mitadt.newui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_design extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mEmailId, mPassword;
    FirebaseAuth fAuth;
    Button LogInBtn;
    TextView GotoReg,ForgotPass;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_design);

        mEmailId = findViewById(R.id.adminEmail);
        mPassword = findViewById(R.id.AdminPass);
        LogInBtn = findViewById(R.id.SignInBtn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        GotoReg = findViewById(R.id.RegOptionText);
        ForgotPass = findViewById(R.id.clickForgotPass);

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();
        }

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailId.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){ //checking whether the email text is empty or not
                    mEmailId.setError("E-mail Id is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){ //checking whether the password field is empty
                    mPassword.setError("Password field is required");
                    return;
                }
                if(pass.length() < 6){  //checking whether the entered password is at-least 6 characters long
                    mPassword.setError("Password must have minimum 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //Visibility of progress bar if all IF conditions are satisfied

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_design.this, "LogGED-In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainPage.class));
                            finish();

                        }
                        else{
                            Toast.makeText(login_design.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility((View.GONE));
                        }

                    }
                });
            }
        });

        GotoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterPortal.class));
                finish();
            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPass.class));
            }
        });






    }
}