package com.mitadt.newui.AeroSpace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;

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
            case R.id.aerodynamics: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));break;
            case R.id.toprop: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.nmethods: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.mtech: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.eninstrument: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;
            case R.id.german: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class)); break;

            default:break;

        }

    }
}