package com.mitadt.newui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class otpAfterRegister extends AppCompatActivity {

    public String NumberEnteredByUser, verificationCodeBySystem, FullName, Email, Pass;
    public static final String TAG = "TAG";
    Button VerifyButton;
    PinView phoneEnteredByUser;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String UserId;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        NumberEnteredByUser = intent.getStringExtra("PhoneNo");
        FullName = intent.getStringExtra("FullName");
        Email = intent.getStringExtra("Email");
        Pass = intent.getStringExtra("Password");


        VerifyButton = findViewById(R.id.otpAfterRegBtn);
        phoneEnteredByUser = findViewById(R.id.EnterCodeForRegister);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


    }


    private void send_code_to_user(String NumberEnteredByUser) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fauth)
                        .setPhoneNumber("+91" + NumberEnteredByUser)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(otpAfterRegister.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                                String code = phoneAuthCredential.getSmsCode();
                                if (code != null) {
                                    finishEverything(code);
                                }
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                //Toast.makeText(VerifyOtp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(VerifyOtp.this, e, Toast.LENGTH_SHORT).show();
                                Toast.makeText(otpAfterRegister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCodeBySystem = s;
                                Toast.makeText(otpAfterRegister.this, "Code sent", Toast.LENGTH_SHORT).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void checkcode() {
        String userEnteredOtp = phoneEnteredByUser.getText().toString();
        if (userEnteredOtp.isEmpty() || userEnteredOtp.length() < 6) {
            Toast.makeText(this, "Wrong Otp!", Toast.LENGTH_SHORT).show();
            return;
        }
        finishEverything(userEnteredOtp);
    }

    private void finishEverything(String code) {
        phoneEnteredByUser.setText(code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, code);
        sign_in(credential);
    }

    private void sign_in(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential).addOnCompleteListener(otpAfterRegister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    fauth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                UserId = auth.getCurrentUser().getUid();

                                DocumentReference documentReference = fstore.collection("USERS").document(NumberEnteredByUser);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fullName", FullName);
                                user.put("E-mail", Email);
                                user.put("Phone-No", NumberEnteredByUser);
                                user.put("Password", Pass);

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
                                Toast.makeText(otpAfterRegister.this, "UserRegisteredSuccessfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainPage.class));
                                finish();
                            } else {
                                Toast.makeText(otpAfterRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }
}







