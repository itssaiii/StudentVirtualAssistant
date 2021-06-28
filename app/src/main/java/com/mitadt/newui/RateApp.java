package com.mitadt.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateApp extends AppCompatActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);
        getSupportActionBar().hide();

        ratingBar = findViewById(R.id.rateourapp);


    }
    public void OnSubmit(View view){
        float value = ratingBar.getRating();
        if(value<2){
            Toast.makeText(this, "Sorry for the inconvenience! We will make it more user friendly", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();
        }
        else if(value<=3 && value>=2){
            Toast.makeText(this, "We will try to make it better", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();

        }
        else if(value<=4 && value>3){
            Toast.makeText(this, "Thankyou for your review. We will keep up the good work", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();

        }
        else if(value<=5 && value>4){
            Toast.makeText(this, "Jeez!! Is it really that good", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();

        }
    }

}