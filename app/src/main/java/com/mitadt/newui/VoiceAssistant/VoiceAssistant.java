package com.mitadt.newui.VoiceAssistant;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.ComputerScience.ZoomLecture;
import com.mitadt.newui.DiscussionForum.AddQuestion;
import com.mitadt.newui.DiscussionForum.ViewAllQuestions;
import com.mitadt.newui.LogInOrSignUp;
import com.mitadt.newui.R;
import com.mitadt.newui.ViewAdsaBooks;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceAssistant extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ImageView voice;
    TextView command,nextcommand;
    TextToSpeech t1;
    public String next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_assistant);

        voice = findViewById(R.id.voiceassistant);
        firebaseAuth = FirebaseAuth.getInstance();
        command = findViewById(R.id.command);
        command.setVisibility(View.INVISIBLE);
        nextcommand = findViewById(R.id.nextcommand);
        nextcommand.setVisibility(View.INVISIBLE);


        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolnabhai();
            }
        });

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    t1.setLanguage(Locale.UK);
                }
            }
        });



    }

    public void bolnabhai() {

        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,6000);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hukum Jahapanah");
        try
        {
            startActivityForResult(intent,1000);
        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Error in Code", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000 && resultCode==RESULT_OK) {

            ArrayList<String> commands = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String raw = commands.get(0);
            String[] arrSplit = raw.split(" ");

            String first = arrSplit[0] + " " + arrSplit[1];
            command.setText(first);

            datastructures(command);
            postquestion(command);
            if(command.getText().toString().equals("open books")) {
                startActivity(new Intent(getApplicationContext(), ViewAdsaBooks.class));

            }
            else if(command.getText().toString().equals("log out")) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LogInOrSignUp.class));
                finish();

            }
            else if(command.getText().toString().equals("doubt forum"))
            {
                startActivity(new Intent(getApplicationContext(), ViewAllQuestions.class));

            }
            else if(command.getText().toString().equals("join lecture"))
            {
                nextcommand.setText("Which Lecture");
                String toSpeak = nextcommand.getText().toString().trim();
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                bolnabhai();


            }
            else if(command.getText().toString().equals("ask question")){
                nextcommand.setText("Which Subject");
                String toSpeak = nextcommand.getText().toString().trim();
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                bolnabhai();

            }
            else {
                Toast.makeText(this, "Invalid Command", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void postquestion(TextView command) {
        if(command.getText().toString().equals("data communication"))
        {

            startActivity(new Intent(getApplicationContext(), AddQuestion.class));
        }


    }

    private void datastructures(TextView command) {
        if(command.getText().toString().equals("data structures"))
        {

            startActivity(new Intent(getApplicationContext(), ZoomLecture.class));
        }


    }
}