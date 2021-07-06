package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mitadt.newui.AeroSpace.AeroSpace;

import com.mitadt.newui.ENTC.ElectronicsSubjects;
import com.mitadt.newui.InfoTech.InformationTechnology;
import com.mitadt.newui.Mechanical.MechanicalSubjects;


import java.util.ArrayList;
import java.util.List;

public class ChooseDomain extends AppCompatActivity  {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_domain);
        getSupportActionBar().hide();

        spinner = findViewById(R.id.spinner);
        List<String> categories = new ArrayList<>();

        categories.add(0, "Choose your Field");
        categories.add("Computer-Science");
        categories.add("Aerospace-Aeronautical");
        categories.add("Information Technology");
        categories.add("Electronics and Telecommunications");
        categories.add("Mechanical");



        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sSelected=parent.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(),
                        categories.get(position),
                        Toast.LENGTH_LONG)
                        .show();

                if(sSelected.equals("Computer-Science"))
                {
                    startActivity(new Intent(getApplicationContext(), com.mitadt.newui.SubjectChoice.class));

                }
                if(sSelected.equals("Information Technology"))
                {
                    startActivity(new Intent(getApplicationContext(), InformationTechnology.class));

                }
                if(sSelected.equals("Aerospace-Aeronautical"))
                {
                    startActivity(new Intent(getApplicationContext(), AeroSpace.class));

                }
                if(sSelected.equals("Electronics and Telecommunications"))
                {
                    startActivity(new Intent(getApplicationContext(), ElectronicsSubjects.class));

                }
                if(sSelected.equals("Mechanical"))
                {
                    startActivity(new Intent(getApplicationContext(), MechanicalSubjects.class));

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}