package com.mitadt.newui.Mechanical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;

public class MechanicalSubjects extends AppCompatActivity implements View.OnClickListener {

    CardView thermo,fm,ampt,tom,em,german;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical_subjects);
        thermo = findViewById(R.id.thermodynamics);
        fm = findViewById(R.id.fluidmechanics);
        ampt = findViewById(R.id.advancedmanufact);
        tom = findViewById(R.id.theoryofmachines);
        em = findViewById(R.id.electricalmachines);
        german = findViewById(R.id.german);

        thermo.setOnClickListener(this);
        fm.setOnClickListener(this);
        ampt.setOnClickListener(this);
        tom.setOnClickListener(this);
        em.setOnClickListener(this);
        german.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.thermodynamics: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));break;
            case R.id.fluidmechanics: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.advancedmanufact: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.theoryofmachines: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.electricalmachines: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;

            default:break;

        }

    }
}