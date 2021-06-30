package com.mitadt.newui.DiscussionForum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitadt.newui.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddQuestion extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText title,description;
    Button upload;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String storetitle,storedescription,email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);


        title = findViewById(R.id.ptitle);
        description = findViewById(R.id.pdes);
        upload = findViewById(R.id.pupload);

        //GETTING INSTANCES FROM FIREBASE
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        //FETCHING THE EMAIL ID OF THE CURRENT SIGNED-IN USER

        email = fAuth.getCurrentUser().getEmail();



        //FETCHING NAME OF CURRENT USER FROM FIRESTORE
        fstore.document("USERS/" + email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.getString("fullName");
            }
        });




        //STORING THE QUESTION DETAILS INTO FIRESTORE

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CONVERTING THE TITLE TEXT AND DESCRIPTION TEXT INTO STRINGS
                storetitle = title.getText().toString().trim();
                storedescription = description.getText().toString().trim();

                //CALENDER IMPLEMENTATION
                Calendar calforDate = Calendar.getInstance();
                Calendar calforTime = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");

                final String savedate = currentDate.format(calforDate.getTime());
                final String savetime = currentTime.format(calforDate.getTime());



                DocumentReference documentReference = fstore.collection("QUESTION").document(storetitle);
                Map<String,Object> user = new HashMap<>();
                user.put("name",name);
                user.put("E-mail",email);
                user.put("title",storetitle);
                user.put("description",storedescription);
                user.put("date",savedate);
                user.put("time",savetime);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "QUESTION HAS BEEN STORED ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "On failure " + e.toString());
                    }
                });
                Toast.makeText(AddQuestion.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ViewAllQuestions.class));

            }
        });
    }
}