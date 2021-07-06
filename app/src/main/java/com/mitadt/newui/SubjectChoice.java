package com.mitadt.newui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

public class SubjectChoice extends AppCompatActivity implements View.OnClickListener {

    CardView adsa,dccn,toc,german,ictt,sepm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_choice);

        adsa = findViewById(R.id.adsa);
        dccn = findViewById(R.id.dccn);
        toc = findViewById(R.id.toc);
        sepm = findViewById(R.id.sepm);
        ictt = findViewById(R.id.ictt);
        german = findViewById(R.id.german);

        //ADDING ON CLICK LISTENER
        adsa.setOnClickListener(this);
        dccn.setOnClickListener(this);
        toc.setOnClickListener(this);
        sepm.setOnClickListener(this);
        ictt.setOnClickListener(this);
        german.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.adsa: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));break;
            case R.id.dccn: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.toc: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.sepm: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.ictt: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;

            default:break;

        }

    }
}