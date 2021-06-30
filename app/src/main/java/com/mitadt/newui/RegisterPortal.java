package com.mitadt.newui;

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

import java.util.HashMap;
import java.util.Map;


public class RegisterPortal extends AppCompatActivity {

    //Declaring variables for all the functions
    public static final String TAG = "TAG";
    EditText mFullName, mEmailId, mPassword, mPhoneNumber;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;
    String UserId,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_portal);
        getSupportActionBar().hide();  // Hiding the topmost bar


        //Getting the entered information into the declared variables
        mFullName = findViewById(R.id.StudentfullName);
        mEmailId = findViewById(R.id.StudentEmail);
        mPassword = findViewById(R.id.StudentPass);
        mRegisterBtn = findViewById(R.id.registerButton);
        mPhoneNumber = findViewById(R.id.StudentPhoneNumber);

        mLoginBtn =  findViewById(R.id.optionText);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){  // IF USER HAS ALREADY LOGGED IN TO THE SYSTEM
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {    //TO SEE WHAT ALL HAPPENS WHEN WE CLICK REG BUTTON
            @Override
            public void onClick(View v) {
                String email = mEmailId.getText().toString().trim();  //storing the email in form of string
                String password = mPassword.getText().toString().trim(); //storing the password in form of a string
                String fname = mFullName.getText().toString().trim();
                String phno = mPhoneNumber.getText().toString().trim();

                if(TextUtils.isEmpty(fname)){
                    mFullName.setError(("Full Name is required"));
                    return;
                }
                if (!isEmailValid(email)){
                    mEmailId.setError("Invalid Address");
                    return;
                }

                if(TextUtils.isEmpty(email)){ //checking whether the email text is empty or not
                    mEmailId.setError("E-mail Id is required");
                    return;
                }

                if(TextUtils.isEmpty(phno)){ //checking whether the phno text is empty or not
                    mPhoneNumber.setError("Phone Number is required");
                    return;
                }

                if(phno.length()<10){
                    mPhoneNumber.setError("Phone Number must contain 10 Digits");

                }

                if(phno.length()>10){
                    mPhoneNumber.setError("Phone Number must contain 10 Digits");

                }



                if(TextUtils.isEmpty(password)){ //checking whether the password field is empty
                    mPassword.setError("Password field is required");
                    return;
                }
                if(password.length() < 6){  //checking whether the entered password is at-least 6 characters long
                    mPassword.setError("Password must have minimum 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //Visibility of progress bar if all IF conditions are satisfied

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(RegisterPortal.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();


                            UserId = fAuth.getCurrentUser().getUid();
                            phone = fAuth.getCurrentUser().getPhoneNumber();
                            DocumentReference documentReference = fstore.collection("USERS").document(email);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fullName",fname);
                            user.put("E-mail",email);
                            user.put("Phone-No",phno);
                            user.put("Password",password);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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

                            Intent intent = new Intent(getApplicationContext(),MainPage.class);
                            intent.putExtra("PhoneNo",phno);
                            intent.putExtra("FullName",fname);
                            intent.putExtra("Email",email);
                            intent.putExtra("Password",password);
                            startActivity(intent);
                            finish();



                        }
                        else {
                            Toast.makeText(RegisterPortal.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login_design.class);
                intent.putExtra("CorrectNo",phone);
                startActivity(intent);
            }
        });

    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}