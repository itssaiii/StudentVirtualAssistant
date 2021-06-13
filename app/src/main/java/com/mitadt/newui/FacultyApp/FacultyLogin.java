package com.mitadt.newui.FacultyApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.Admin.AdminDashboard;
import com.mitadt.newui.R;

public class FacultyLogin extends AppCompatActivity {
    EditText Teachermail, Teacherpass;
    Button Teacherlog;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView ContactAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        getSupportActionBar().hide();

        Teachermail = findViewById(R.id.TeacherEmail);
        Teacherpass = findViewById(R.id.TeacherPass);
        Teacherlog = findViewById(R.id.TeacherSignIn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.TeacherProgressBar);
        ContactAdmin = findViewById(R.id.textView13);

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), FacultyDashboard.class));
            finish();
        }

        Teacherlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = Teachermail.getText().toString().trim();
                String password = Teacherpass.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){ //checking whether the email text is empty or not
                    Teachermail.setError("E-mail Id is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){ //checking whether the password field is empty
                    Teacherpass.setError("Password field is required");
                    return;
                }
                if(password.length() < 6){  //checking whether the entered password is at-least 6 characters long
                    Teacherpass.setError("Password must have minimum 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //Visibility of progress bar if all IF conditions are satisfied

                fAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FacultyLogin.this, "Logged-In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),FacultyDashboard.class));
                            finish();
                        }
                        else{
                            Toast.makeText(FacultyLogin.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        ContactAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}