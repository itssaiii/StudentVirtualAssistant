package com.mitadt.newui.ComputerScience;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mitadt.newui.R;

public class JoinAdsaLecture extends AppCompatActivity {

    TextView textTopic,textDate,TextTime;
    String topic,date,time,link;
    String retrievedTopic,retrievedDate,retrievedLink,retrievedTime;
    Button join;
    Uri uri;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_adsa_lecture);

        textTopic = findViewById(R.id.topicView);
        textDate = findViewById(R.id.DateView);
        TextTime= findViewById(R.id.TimeView);
        join = findViewById(R.id.joinButton);



        Intent intent =getIntent();
        topic = intent.getStringExtra("Topic");
        link = intent.getStringExtra("Link");
        date = intent.getStringExtra("Date");
        time = intent.getStringExtra("Time");

        //RETRIVING INFORMATION FROM FIREBASE


        fstore = FirebaseFirestore.getInstance();
        fstore.document("LECTURES/"+ "Adsa").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    retrievedLink = documentSnapshot.getString("Meeting Link");
                    retrievedDate = documentSnapshot.getString("Date");
                    retrievedTime = documentSnapshot.getString("Time");
                    retrievedTopic = documentSnapshot.getString("Topic");

                    textTopic.setText("TOPIC: " + retrievedTopic);
                    textDate.setText("DATE: "+ retrievedDate);
                    TextTime.setText("TIME: " + retrievedTime);
                }
            }
        });



        //




        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(retrievedLink); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
}