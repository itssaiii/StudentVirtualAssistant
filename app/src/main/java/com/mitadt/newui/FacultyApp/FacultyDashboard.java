package com.mitadt.newui.FacultyApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.ComputerScience.Adsa_Books;
import com.mitadt.newui.ComputerScience.ScheduleMeetingAdsa;
import com.mitadt.newui.FacultyProfile;
import com.mitadt.newui.LogInOrSignUp;
import com.mitadt.newui.R;

public class FacultyDashboard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    Button Signout;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder builder;

    CardView revision,prof,books;
    DrawerLayout drawerLayout;
    NavigationView sidebar;
    Toolbar toolbar;
    Menu menu;
    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        getSupportActionBar().hide();


        //CARDViews Type casting
        revision = findViewById(R.id.revisionCardView);
        prof = findViewById(R.id.ProfileCardView);
        books = findViewById(R.id.AttendanceCardView);

        revision.setOnClickListener(this);
        prof.setOnClickListener(this);
        books.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        sidebar = findViewById(R.id.faculty_sidebar_view);
        toolbar = findViewById(R.id.facultyToolbar);
        fAuth = FirebaseAuth.getInstance();
        menu = sidebar.getMenu();

        sidebar.setItemIconTintList(null);
        toolbar.setTitle("Faculty Dashboard");
        sidebar.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        sidebar.setNavigationItemSelectedListener(this);



        Signout = findViewById(R.id.TeacherSignout);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(FacultyDashboard.this);
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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FacultyProfile.class));
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ScheduleMeetingAdsa.class));
                break;

            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                signout();



                break;
            case R.id.share:
                Toast.makeText(this, "Share our App", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rate:
                Toast.makeText(this, "Rate our App", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }

        return true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.revisionCardView:
                Toast.makeText(this, "Revision Portal", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Adsa_Books.class));

                break;
            case R.id.ProfileCardView:
                Toast.makeText(this, "Profile Page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AttendanceCardView:
                Toast.makeText(this, "Attendance Portal", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ScheduleMeetingAdsa.class));
                break;

            default:break;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }

    public void signout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LogInOrSignUp.class));
        finish();
    }


}