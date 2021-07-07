package com.mitadt.newui.ComputerScience;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mitadt.newui.R;

import java.util.ArrayList;
import java.util.List;

public class ZoomLecture extends AppCompatActivity {

    FirebaseFirestore fstore;
    RecyclerView recview;
    ArrayList<ZoomfileHolder> lecturelist;
    ZoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_lecture);

        //type casting
        fstore = FirebaseFirestore.getInstance();
        lecturelist = new ArrayList<>();
        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZoomAdapter(lecturelist);
        recview.setAdapter(adapter);

        fstore.collection("LECTURES").orderBy("LectureDate", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            ZoomfileHolder obj = d.toObject(ZoomfileHolder.class);
                            lecturelist.add(obj);

                        }
                        adapter.notifyDataSetChanged();

                    }
                });

    }
}