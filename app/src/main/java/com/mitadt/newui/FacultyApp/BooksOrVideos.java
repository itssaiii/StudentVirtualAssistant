package com.mitadt.newui.FacultyApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mitadt.newui.ComputerScience.Adsa_Books;
import com.mitadt.newui.ComputerScience.JoinAdsaLecture;
import com.mitadt.newui.DiscussionForum.ViewAllQuestions;
import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;
import com.mitadt.newui.Videos.MainActivity;
import com.mitadt.newui.ViewAdsaBooks;

public class BooksOrVideos extends AppCompatActivity {

    private ListView mlistView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_or_videos);

        mlistView = findViewById(R.id.listbooksview);

        final String[] text = {"Books And Pdf", "Video Library", " "};

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, text);
        mlistView.setAdapter(mAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(BooksOrVideos.this, "Books Portal", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Adsa_Books.class));
                        break;
                    case 1:
                        Toast.makeText(BooksOrVideos.this, "Video Library", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;

                    default:
                        break;

                }
            }
        });




    }
}