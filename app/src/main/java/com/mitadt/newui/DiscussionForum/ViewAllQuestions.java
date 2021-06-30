package com.mitadt.newui.DiscussionForum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mitadt.newui.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllQuestions extends AppCompatActivity {

    FloatingActionButton fb;
    RecyclerView recview;
    ArrayList<questionFileholder> datalist;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    questionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_questions);

        //TYPE CASTING FIREBASE
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        //TYPECASTING FLOATING ACTION BUTTON
        fb = findViewById(R.id.addquestion);

        //SETTING ON CLICK LISTENER FOR FLOATING BUTTON
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddQuestion.class));
            }
        });

        //TYPECASTING REC-VIEW VARIABLE
        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        //INITIALISING ARRAY LIST
        datalist = new ArrayList<>();

        //ADAPTERS TYPECASTING
        adapter = new questionAdapter(datalist);
        recview.setAdapter(adapter);


        //BRINGING THE DATA FROM FIRE-STORE
        fstore.collection("QUESTION").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments(); //getting all docs in a list
                        for(DocumentSnapshot d:list){
                            questionFileholder obj = d.toObject(questionFileholder.class);
                            datalist.add(obj);
                        }

                        //UPDATING ADAPTER
                        adapter.notifyDataSetChanged();


                    }
                });


    }
}