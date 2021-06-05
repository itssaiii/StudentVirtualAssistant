package com.mitadt.newui.ENTC;

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
import com.mitadt.newui.R;

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
            case R.id.adsa: startActivity(new Intent(getApplicationContext(), AdsaTeachers.class));break;
            case R.id.controlSystems: startActivity(new Intent(getApplicationContext(), DccnTeachers.class)); break;
            case R.id.analogCommunication: startActivity(new Intent(getApplicationContext(), TocTeachers.class)); break;
            case R.id.computerOrganisation: startActivity(new Intent(getApplicationContext(), SepmTeachers.class)); break;
            case R.id.linearIntegratedCircuit: startActivity(new Intent(getApplicationContext(), IcttTeachers.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), GermanTeachers.class)); break;


            default:break;

        }

    }
}