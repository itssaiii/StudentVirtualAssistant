package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mitadt.newui.ComputerScience.Adsa_Books;
import com.mitadt.newui.ComputerScience.DccnBooks;
import com.mitadt.newui.ComputerScience.JoinAdsaLecture;
import com.mitadt.newui.ComputerScience.ScheduleMeetingAdsa;

public class SelectOptionsRevision extends AppCompatActivity {
    private ListView mlistView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options_revision);


        mlistView = findViewById(R.id.listview);

        final String[] text = {"Books And Pdf", "Video Library", "Doubt Forum", "Feedback Forum","Join Lecture"," "};

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, text);
        mlistView.setAdapter(mAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(SelectOptionsRevision.this, "Books Portal", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DccnBooks.class));
                        break;
                    case 1:
                        Toast.makeText(SelectOptionsRevision.this, "Video Library", Toast.LENGTH_SHORT).show();

                        break;

                    case 2:
                        Toast.makeText(SelectOptionsRevision.this, "Doubt Forum", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        Toast.makeText(SelectOptionsRevision.this, "Feedback Forum", Toast.LENGTH_SHORT).show();

                        break;

                    case 4:
                        Toast.makeText(SelectOptionsRevision.this, "Join Lecture", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), JoinAdsaLecture.class));
                        break;

                    default:
                        break;

                }
            }
        });
    }
}