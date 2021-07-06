package com.mitadt.newui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mitadt.newui.ComputerScience.JoinAdsaLecture;
import com.mitadt.newui.DiscussionForum.ViewAllQuestions;
import com.mitadt.newui.Videos.MainActivity;

public class SelectOptionsRevision extends AppCompatActivity {
    private ListView mlistView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options_revision);


        mlistView = findViewById(R.id.listview);

        final String[] text = {"Books And Pdf", "Doubt Forum", " Video Library", "Feedback Forum","Join Lecture", " "};

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, text);
        mlistView.setAdapter(mAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(SelectOptionsRevision.this, "Books Portal", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ViewAdsaBooks.class));
                        break;
                    case 1:
                        Toast.makeText(SelectOptionsRevision.this, "Doubt Forum", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ViewAllQuestions.class));
                        break;

                    case 2:
                        Toast.makeText(SelectOptionsRevision.this, "Video Library", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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