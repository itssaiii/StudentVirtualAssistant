package com.mitadt.newui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import java.util.concurrent.TimeUnit;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.SurfaceHolder.Callback;
import android.icu.util.MeasureUnit;


import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

public class otpAfterRegister extends AppCompatActivity {

    public String NumberEnteredByUser,verificationCodeBySystem;
    Button VerifyButton;
    PinView phoneEnteredByUser;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();

        Intent intent =getIntent();
        NumberEnteredByUser = intent.getStringExtra("phoneNo");



        VerifyButton = findViewById(R.id.otpAfterRegBtn);
        phoneEnteredByUser = findViewById(R.id.EnterCodeForRegister);
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
        auth.signInWithCredential(credential).addOnCompleteListener(otpAfterRegister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(otpAfterRegister.this, "UserSignedInSuccessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainPage.class));
                }
                else {
                    Toast.makeText(otpAfterRegister.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
}