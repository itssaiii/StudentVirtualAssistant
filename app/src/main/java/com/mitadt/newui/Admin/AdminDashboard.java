package com.mitadt.newui.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.FacultyApp.FacultyLogin;
import com.mitadt.newui.LogInOrSignUp;
import com.mitadt.newui.MainPage;
import com.mitadt.newui.R;
import com.mitadt.newui.RegisterPortal;
import com.mitadt.newui.login_design;

public class AdminDashboard extends AppCompatActivity {

    Button RegisterFaculty;
    Button logout;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        RegisterFaculty = findViewById(R.id.facultyreg);
        logout = findViewById(R.id.signoutAdmin);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(AdminDashboard.this);
                builder.setTitle("SIGN-OUT");
                builder.setMessage("Do you want to Logout");
                builder.setCancelable(true); //SETS WHETHER THE DIALOG IS CANCELABLE OR NOT
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog.cancel();
                        FirebaseAuth.getInstance().signOut(); //SIGNS OUT OF FIREBASE
                        startActivity(new Intent(getApplicationContext(), LogInOrSignUp.class));
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

        RegisterFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FacultyRegistration.class));
                finish();
            }
        });
    }
}