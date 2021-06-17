package com.mitadt.newui.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.MainPage;
import com.mitadt.newui.R;

public class UniversityPortal extends AppCompatActivity {

    EditText UnivEmail,UnivPass;
    Button UnivSignIn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_portal);
        getSupportActionBar().hide();

        UnivEmail = findViewById(R.id.adminEmail);
        UnivPass = findViewById(R.id.AdminPass);
        UnivSignIn = findViewById(R.id.AdminLogIn);
        progressBar = findViewById(R.id.progressBarUniv);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
            finish();
        }

        UnivSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminMail = UnivEmail.getText().toString().trim();
                String adminPass = UnivPass.getText().toString().trim();


                if(TextUtils.isEmpty(adminMail)){ //checking whether the email text is empty or not
                    UnivEmail.setError("E-mail Id is required");
                    return;
                }
                if(TextUtils.isEmpty(adminPass)){ //checking whether the password field is empty
                    UnivPass.setError("Password field is required");
                    return;
                }
                if(adminPass.length() < 6){  //checking whether the entered password is at-least 6 characters long
                    UnivPass.setError("Password must have minimum 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //Visibility of progress bar if all IF conditions are satisfied

                fAuth.signInWithEmailAndPassword(adminMail,adminPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UniversityPortal.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
                            finish();
                        }
                        else {
                            Toast.makeText(UniversityPortal.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility((View.GONE));
                        }

                    }
                });
            }
        });



    }
}