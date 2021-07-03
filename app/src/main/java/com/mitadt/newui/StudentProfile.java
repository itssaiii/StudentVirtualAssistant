package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentProfile extends AppCompatActivity {

    TextView fullname;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        getSupportActionBar().hide();

        fullname  = findViewById(R.id.fullname);

        //FIRESTORE
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        email =fAuth.getCurrentUser().getEmail();
        fullname.setText(email);





    }

    public void ResetPass(View view){
        startActivity(new Intent(getApplicationContext(),ResetPassword.class));
    }
}