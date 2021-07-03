package com.mitadt.newui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mitadt.newui.ComputerScience.Adsa_Books;
import com.mitadt.newui.VoiceAssistant.VoiceAssistant;

import java.util.ArrayList;
import java.util.Locale;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Button logout;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder builder;
    BottomNavigationView bottomNavigationView;
    CardView revision,library,planner;
    DrawerLayout drawerLayout;
    NavigationView sidebar;
    Toolbar toolbar;
    Menu menu;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();

        //VOICE ASSISTANT


        bottomNavigationView = findViewById(R.id.bottomNavbar);
        logout = findViewById(R.id.StudentSignout);


        //SIDEBAR INTEGRATION
        sidebar = findViewById(R.id.student_sidebar_view);
        toolbar = findViewById(R.id.studentToolbar);
        drawerLayout = findViewById(R.id.student_drawer_layout);
        menu = sidebar.getMenu();

        sidebar.setItemIconTintList(null);
        toolbar.setTitle("Student Dashboard");
        sidebar.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        sidebar.setNavigationItemSelectedListener(this);



        //CARD VIEW OBJECTS
        revision = findViewById(R.id.revisionCardView);
        planner = findViewById(R.id.DailyPlanner);
        library = findViewById(R.id.VirtualLibrary);

        revision.setOnClickListener(this);
        planner.setOnClickListener(this);
        library.setOnClickListener(this);



        //LOGOUT FROM DASHBOARD USING DIALOG BOX

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
                        startActivity(new Intent(getApplicationContext(),LogInOrSignUp.class));
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



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),StudentProfile.class));
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), VoiceAssistant.class));
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
                startActivity(new Intent(getApplicationContext(),RateApp.class));
                break;
            default:
                break;

        }
        return true;
    }

    public void signout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LogInOrSignUp.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.DailyPlanner:
                Toast.makeText(this, "Daily Planner Portal", Toast.LENGTH_SHORT).show();
                break;

            case R.id.revisionCardView:
                Toast.makeText(this, "Revision Portal", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ChooseDomain.class));
                break;

            case R.id.VirtualLibrary:
                Toast.makeText(this, "Virtual Library", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }
}
