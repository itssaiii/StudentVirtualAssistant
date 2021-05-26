package com.mitadt.newui.Mechanical;

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
            case R.id.thermodynamics: startActivity(new Intent(getApplicationContext(), AdsaTeachers.class));break;
            case R.id.fluidmechanics: startActivity(new Intent(getApplicationContext(), DccnTeachers.class)); break;
            case R.id.advancedmanufact: startActivity(new Intent(getApplicationContext(), TocTeachers.class)); break;
            case R.id.theoryofmachines: startActivity(new Intent(getApplicationContext(), SepmTeachers.class)); break;
            case R.id.electricalmachines: startActivity(new Intent(getApplicationContext(), IcttTeachers.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), GermanTeachers.class)); break;

            default:break;

        }

    }
}