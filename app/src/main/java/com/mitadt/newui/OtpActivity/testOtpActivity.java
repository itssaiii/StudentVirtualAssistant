package com.mitadt.newui.OtpActivity;

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

import com.mitadt.newui.MainPage;
import com.mitadt.newui.R;

public class testOtpActivity extends AppCompatActivity {

    public String NumberEnteredByUser,verificationCodeBySystem;
    Button VerifyButton;
    PinView phoneEnteredByUser;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_otp);
        getSupportActionBar().hide();
        VerifyButton = findViewById(R.id.btnVerify);
        phoneEnteredByUser = findViewById(R.id.EnterCode);
        auth = FirebaseAuth.getInstance();

        Intent intent =getIntent();
        NumberEnteredByUser = intent.getStringExtra("phoneNo");

        //authentication starts




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
        auth.signInWithCredential(credential).addOnCompleteListener(testOtpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(testOtpActivity.this, "UserSignedInSuccessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainPage.class));
                }
                else {
                    Toast.makeText(testOtpActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void send_code_to_user(String NumberEnteredByUser ) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91" + NumberEnteredByUser)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(testOtpActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                        String code = phoneAuthCredential.getSmsCode();
                        if (code != null) {
                            finishEverything(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(testOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCodeBySystem = s;
                        Toast.makeText(testOtpActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}
