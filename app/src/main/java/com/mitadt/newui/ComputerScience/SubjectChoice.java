package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mitadt.newui.ComputerScience.AdsaTeachers;
import com.mitadt.newui.ComputerScience.DccnTeachers;
import com.mitadt.newui.ComputerScience.GermanTeachers;
import com.mitadt.newui.ComputerScience.IcttTeachers;
import com.mitadt.newui.ComputerScience.SepmTeachers;
import com.mitadt.newui.ComputerScience.TocTeachers;

public class SubjectChoice extends AppCompatActivity implements View.OnClickListener {

    CardView adsa,dccn,toc,german,ictt,sepm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            case R.id.adsa: startActivity(new Intent(getApplicationContext(), AdsaTeachers.class));break;
            case R.id.dccn: startActivity(new Intent(getApplicationContext(), DccnTeachers.class)); break;
            case R.id.toc: startActivity(new Intent(getApplicationContext(), TocTeachers.class)); break;
            case R.id.sepm: startActivity(new Intent(getApplicationContext(), SepmTeachers.class)); break;
            case R.id.ictt: startActivity(new Intent(getApplicationContext(), IcttTeachers.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), GermanTeachers.class)); break;

            default:break;

        }

    }
}