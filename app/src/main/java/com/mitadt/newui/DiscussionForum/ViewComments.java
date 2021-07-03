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

public class ViewComments extends AppCompatActivity {

    FloatingActionButton fb;
    String sendtitle;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String randomPostkey,UserId,email,getname;
    RecyclerView recview;
    ArrayList<commentfileholder> answerlist;
    commentAdapter adapter;
    String description;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);

        sendtitle = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fb = findViewById(R.id.pleaseaddcomment);

        email = fAuth.getCurrentUser().getEmail();

        fstore.document("USERS/" + email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        getname = documentSnapshot.getString("fullName");
                    }
                });



        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        answerlist = new ArrayList<>();


        adapter = new commentAdapter(answerlist);
        recview.setAdapter(adapter);

        fstore.collection("/QUESTION/" + sendtitle + "/" + "COMMENTS/").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments(); //getting all docs in a list
                        for(DocumentSnapshot g:list){
                            commentfileholder com = g.toObject(commentfileholder.class);
                            answerlist.add(com);

                        }

                        //UPDATING ADAPTER
                        adapter.notifyDataSetChanged();


                    }


                });










        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddAnswer.class);
                intent.putExtra("title",sendtitle);
                intent.putExtra("description",description);
                startActivity(intent);
            }
        });
    }
}