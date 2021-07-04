package com.mitadt.newui.DiscussionForum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AddAnswer extends AppCompatActivity {

    public static final String TAG = "TAG";

    //FETCHING TO RECYCLER VIEW
    RecyclerView recview;
    ArrayList<commentfileholder> answerlist;
    commentAdapter adapter;



    FloatingActionButton add;
    String storecomment;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String title,description;
    String randomPostkey,UserId,email,getname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);

        //INSTANCING OF FIREBASE
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        //getting mail and name;

        email = fAuth.getCurrentUser().getEmail();
        fstore.document("USERS/" + email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        getname = documentSnapshot.getString("fullName");
                    }
                });




        answerlist = new ArrayList<>();


        adapter = new commentAdapter(answerlist);






        fstore.collection("/QUESTION/" + title + "/" + "COMMENTS/").get()
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














        //STORING THE COMMENT INSIDE FIRE-STORE
        add = findViewById(R.id.addanswer);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AddAnswer.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_view);
                bottomSheetDialog.setCanceledOnTouchOutside(false);
                bottomSheetDialog.show();

                //INITIALISE THE SET BOTTOM SHEET VARIABLES AND TYPECAST THEM

                EditText entercomment;
                ImageView send;

                entercomment = bottomSheetDialog.findViewById(R.id.entercomment);
                send = bottomSheetDialog.findViewById(R.id.sendbutton);

                //CALENDER IMPLEMENTATION
                Calendar calforDate = Calendar.getInstance();
                Calendar calforTime = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");

                final String savedate = currentDate.format(calforDate.getTime());
                final String savetime = currentTime.format(calforDate.getTime());
                //


                title = getIntent().getStringExtra("title");
                description = getIntent().getStringExtra("description");

                UserId = fAuth.getCurrentUser().getUid();
                randomPostkey = UserId+""+new Random().nextInt(1000);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            storecomment = entercomment.getText().toString().trim();

                            DocumentReference documentReference = fstore.collection("QUESTION").document(title).collection("COMMENTS").document(randomPostkey);
                            Map<String,Object> user = new HashMap<>();
                            user.put("comment",storecomment);
                            user.put("cemail",email);
                            user.put("cname",getname);
                            user.put("cques",description);
                            user.put("ctime",savetime);
                            user.put("cdate",savedate);


                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Comment has been Added Successfully " + UserId);
                                    add.setVisibility(View.VISIBLE);
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(AddAnswer.this, "Comment has been Added Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "On failure " + e.toString());

                                }
                            });


                    }

                });
            }
        });
    }
}