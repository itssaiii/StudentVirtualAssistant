package com.mitadt.newui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {

    public static final String TAG = "TAG";

    TextInputEditText newpass,reconfirm;
    Button resetbtn;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String firstpass, nextpass,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().hide();

        fstore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();

        resetbtn = findViewById(R.id.resetpassbutton);
        newpass = findViewById(R.id.newpasswordset);
        reconfirm = findViewById(R.id.reconfirmpassword);

        email = fauth.getCurrentUser().getEmail();

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                firstpass = newpass.getText().toString().trim();
                nextpass = reconfirm.getText().toString().trim();

                if(firstpass.matches("")){
                    newpass.setError("Password cannot be empty");
                    return;
                }
                if(nextpass.matches("")){
                    reconfirm.setError("Password cannot be empty");
                    return;
                }
                if(firstpass.length()<6){
                    newpass.setError("Password must have minimum 6 characters");
                    return;
                }
                if(nextpass.length()<6){
                    reconfirm.setError("Password must have minimum 6 characters");
                    return;
                }
                if(!firstpass.equals(nextpass)){
                    Toast.makeText(ResetPassword.this, "Password entered doesn't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(firstpass.equals(nextpass)){
                    Map<String,Object> user = new HashMap<>();
                    user.put("Password",nextpass);
                    fstore.collection("USERS").document(email).set(user, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ResetPassword.this, "Your Password has Been Reset", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "On failure " + e.toString());
                        }
                    });
                }

            }
        });





    }
}