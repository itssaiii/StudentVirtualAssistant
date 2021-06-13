package com.mitadt.newui.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitadt.newui.MainPage;
import com.mitadt.newui.R;
import com.mitadt.newui.RegisterPortal;

import java.util.HashMap;
import java.util.Map;

public class FacultyRegistration extends AppCompatActivity {
    //Declaring variables for all the functions
    public static final String TAG = "TAG";
    EditText FacultyName, FacultyEmail, FacultyPhoneNumber, FacultyPassword;
    Button FacultyRegister;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;
    String UserId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_registration);
        getSupportActionBar().hide();

        FacultyName = findViewById(R.id.FacultyfullName);
        FacultyEmail = findViewById(R.id.FacultyEmail);
        FacultyPassword = findViewById(R.id.FacultyPass);
        FacultyPhoneNumber = findViewById(R.id.FacultyPhoneNumber);
        FacultyRegister = findViewById(R.id.FacultyReg);
        progressBar = findViewById(R.id.facultyprogressBar);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        FacultyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = FacultyEmail.getText().toString().trim();
                String phno = FacultyPhoneNumber.getText().toString().trim();
                String fname = FacultyName.getText().toString().trim();
                String pass = FacultyPassword.getText().toString().trim();

                if(TextUtils.isEmpty(fname)){
                    FacultyName.setError(("Full Name is required"));
                    return;
                }

                if(TextUtils.isEmpty(email)){ //checking whether the email text is empty or not
                    FacultyEmail.setError("E-mail Id is required");
                    return;
                }

                if(TextUtils.isEmpty(phno)){ //checking whether the phno text is empty or not
                    FacultyPhoneNumber.setError("Phone Number is required");
                    return;
                }

                if(phno.length()<10){
                    FacultyPhoneNumber.setError("Phone Number must contain 10 Digits");

                }

                if(phno.length()>10){
                    FacultyPhoneNumber.setError("Phone Number must contain 10 Digits");

                }



                if(TextUtils.isEmpty(pass)){ //checking whether the password field is empty
                    FacultyPassword.setError("Password field is required");
                    return;
                }
                if(pass.length() < 6){  //checking whether the entered password is at-least 6 characters long
                    FacultyPassword.setError("Password must have minimum 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //Visibility of progress bar if all IF conditions are satisfied

                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FacultyRegistration.this, "Added Faculty to Database", Toast.LENGTH_SHORT).show();

                            UserId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReferences = fstore.collection("FACULTY").document(fname);
                            Map<String,Object> users = new HashMap<>();
                            users.put("fullName",fname);
                            users.put("E-mail",email);
                            users.put("Phone-No",phno);
                            users.put("Password",pass);

                            documentReferences.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "On success: User profile is created for " + UserId);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "On failure " + e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
                            finish();

                        }
                        else {
                            Toast.makeText(FacultyRegistration.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });
    }
    
}