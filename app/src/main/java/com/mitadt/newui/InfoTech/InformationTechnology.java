package com.mitadt.newui.InfoTech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mitadt.newui.R;
import com.mitadt.newui.SelectOptionsRevision;

public class InformationTechnology extends AppCompatActivity implements View.OnClickListener {

    CardView adsa,wt,cn,ictt,sepm,french;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_technology);

        adsa = findViewById(R.id.adsa);
        wt = findViewById(R.id.webtech);
        cn = findViewById(R.id.compnetwork);
        ictt = findViewById(R.id.ictt);
        sepm = findViewById(R.id.sepm);
        french = findViewById(R.id.french);


        //ADDING ON CLICK LISTENER
        adsa.setOnClickListener(this);
        wt.setOnClickListener(this);
        cn.setOnClickListener(this);
        sepm.setOnClickListener(this);
        ictt.setOnClickListener(this);
        french.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.adsa: startActivity(new Intent(getApplicationContext(), SelectOptionsRevision.class));break;
            case R.id.webtech: startActivity(new Intent(getApplicationContext(),SelectOptionsRevision.class)); break;
            case R.id.compnetwork: startActivity(new Intent(getApplicationContext(),SelectOptionsRevision.class)); break;
            case R.id.sepm: startActivity(new Intent(getApplicationContext(),SelectOptionsRevision.class)); break;
            case R.id.ictt: startActivity(new Intent(getApplicationContext(),SelectOptionsRevision.class)); break;
            case R.id.french: startActivity(new Intent(getApplicationContext(),SelectOptionsRevision.class)); break;

            default:break;

        }

    }
}