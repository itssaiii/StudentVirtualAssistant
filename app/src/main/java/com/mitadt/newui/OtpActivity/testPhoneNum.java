package com.mitadt.newui.OtpActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

import com.mitadt.newui.R;

public class testPhoneNum extends AppCompatActivity {

    public String NumberEnteredByUser;
    EditText mPhoneNumber;
    Button GenerateOtp;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_phone_num);

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        mPhoneNumber = findViewById(R.id.numberphone);
        GenerateOtp = findViewById(R.id.proceedBtn);



        GenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = mPhoneNumber.getText().toString().trim();




                if(num.isEmpty()){
                    mPhoneNumber.setError("Phone Number is required");
                    return;
                }
                if(num.length()<10){
                    mPhoneNumber.setError("Phone Number must contain 10 digits");
                    return;
                }
                if (num.length()>10)
                {
                    mPhoneNumber.setError("Phone Number must contain 10 digits");
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),testOtpActivity.class);
                intent.putExtra("phoneNo",num);
                startActivity(intent);


            }
        });
    }
}