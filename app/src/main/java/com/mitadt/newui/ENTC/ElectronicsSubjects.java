package com.mitadt.newui.ENTC;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;

public class ElectronicsSubjects extends AppCompatActivity implements View.OnClickListener{

    CardView adsa,control,analog,co,lic,german;  //DECLARED VARIABLES FOR EACH SUBJECT AS A CARD VIEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics_subjects);

        //INITIALISED ALL VARIABLES WITH ITS CONTENT BY PROVIDING THE ID OF EACH SUBJECT/CARD VIEW
        adsa = findViewById(R.id.adsa);
        control = findViewById(R.id.controlSystems);
        analog = findViewById(R.id.analogCommunication);
        co = findViewById(R.id.computerOrganisation);
        lic = findViewById(R.id.linearIntegratedCircuit);
        german = findViewById(R.id.german);

        //HERE WE ARE SETTING AN ONCLICK LISTENER SO THAT WHEN THE USER CLICKS THE CARD VIEW IT SHOULD RESPOND
        //THEREFORE EVERY VARIABLE THAT HAS BEEN DECLARED HAS BEEN SET TO A CLICK LISTENER FUNCTION
        adsa.setOnClickListener(this);
        control.setOnClickListener(this);
        analog.setOnClickListener(this);
        co.setOnClickListener(this);
        lic.setOnClickListener(this);
        german.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //HERE WE ARE TAKING A SWITCH CASE SO THAT WHEN THE USER CLICKS THE CARD VIEW, THE RESPONSE SHOULD BE TO GO TO THE NEXT ACTIVITY
        switch (v.getId()){
            case R.id.adsa: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));break;
            case R.id.controlSystems: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.analogCommunication: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.computerOrganisation: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.linearIntegratedCircuit: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;


            default:break;

        }

    }
}