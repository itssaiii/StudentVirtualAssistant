package com.mitadt.newui.AeroSpace;

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

public class AeroSpace extends AppCompatActivity implements View.OnClickListener {

    CardView aero,top,mt,nm,eni,german;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aero_space);

        aero = findViewById(R.id.aerodynamics);
        top = findViewById(R.id.toprop);
         nm = findViewById(R.id.nmethods);
        mt = findViewById(R.id.mtech);
        eni = findViewById(R.id.eninstrument);
        german = findViewById(R.id.german);

        //ADDING ON CLICK LISTENER
        aero.setOnClickListener(this);
        top.setOnClickListener(this);
        nm.setOnClickListener(this);
        mt.setOnClickListener(this);
        eni.setOnClickListener(this);
        german.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.aerodynamics: startActivity(new Intent(getApplicationContext(), AdsaTeachers.class));break;
            case R.id.toprop: startActivity(new Intent(getApplicationContext(), DccnTeachers.class)); break;
            case R.id.nmethods: startActivity(new Intent(getApplicationContext(), TocTeachers.class)); break;
            case R.id.mtech: startActivity(new Intent(getApplicationContext(), SepmTeachers.class)); break;
            case R.id.eninstrument: startActivity(new Intent(getApplicationContext(), IcttTeachers.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), GermanTeachers.class)); break;

            default:break;

        }

    }
}