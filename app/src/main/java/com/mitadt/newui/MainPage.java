package com.mitadt.newui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    Button logout;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder builder;
    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottomNavbar);

        logout = findViewById(R.id.signout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(MainPage.this);
                builder.setTitle("SIGN-OUT");
                builder.setMessage("Do you want to Logout");
                builder.setCancelable(true); //SETS WHETHER THE DIALOG IS CANCELABLE OR NOT
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog.cancel();
                        FirebaseAuth.getInstance().signOut(); //SIGNS OUT OF FIREBASE
                        startActivity(new Intent(getApplicationContext(),login_design.class));
                        finish();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog.cancel(); //CANCELS THE DIALGOE
                    }
                });
                mAlertDialog = builder.create();
                mAlertDialog.show();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.home:
                        Toast.makeText(MainPage.this, "This is Home Page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainPage.class));
                        break;
                    case R.id.plan:
                        Toast.makeText(MainPage.this, "This is virtual Assistant", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.doubt:
                        startActivity(new Intent(getApplicationContext(),ChooseDomain.class));
                        break;
                    case R.id.library:
                        Toast.makeText(MainPage.this, "This is Library", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }





    public void forgotPass(View view){
        startActivity(new Intent(getApplicationContext(),ForgotPass.class));

    }

    public void ChooseField(View view){
        startActivity(new Intent(getApplicationContext(),ChooseDomain.class));
        
    }


}
