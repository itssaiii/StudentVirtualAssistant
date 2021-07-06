package com.mitadt.newui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtp extends AppCompatActivity {

    public String NumberEnteredByUser,verificationCodeBySystem;
    Button VerifyButton;
    PinView phoneEnteredByUser;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();

        Intent intent =getIntent();
        NumberEnteredByUser = intent.getStringExtra("phoneNo");



        VerifyButton = findViewById(R.id.btnVerify);
        phoneEnteredByUser = findViewById(R.id.EnterCode);
        auth = FirebaseAuth.getInstance();


        send_code_to_user(NumberEnteredByUser);




        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkcode();
            }
        });

    }


    private void checkcode() {
        String userEnteredOtp = phoneEnteredByUser.getText().toString();
        if(userEnteredOtp.isEmpty() || userEnteredOtp.length()<6){
            Toast.makeText(this, "Wrong Otp!", Toast.LENGTH_SHORT).show();
            return;
        }
        finishEverything(userEnteredOtp);
    }

    private void finishEverything(String code) {
        phoneEnteredByUser.setText(code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,code);
        sign_in(credential);
    }

    private void sign_in(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential).addOnCompleteListener(VerifyOtp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(VerifyOtp.this, "UserSignedInSuccessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ResetPassword.class));
                }
                else {
                    Toast.makeText(VerifyOtp.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void send_code_to_user(String NumberEnteredByUser ) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91" + NumberEnteredByUser)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(VerifyOtp.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                                String code = phoneAuthCredential.getSmsCode();
                                if (code != null) {
                                    finishEverything(code);
                                }
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                //Toast.makeText(VerifyOtp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(VerifyOtp.this, e, Toast.LENGTH_SHORT).show();
                                Toast.makeText(VerifyOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCodeBySystem = s;
                                Toast.makeText(VerifyOtp.this, "Code sent", Toast.LENGTH_SHORT).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}

