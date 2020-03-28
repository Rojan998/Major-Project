package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Guide_section extends AppCompatActivity {

    private Button button_my_profile,
            button_organic_waste,
            button_my_schedule,
            button_my_location,
            button_my_notifications,
            button_about_app,
            button_project_team,
            button_acknowledgement,
            button_signout;
    private FirebaseAuth firebaseAuth;
/*
    private DrawerLayout drawerLayout;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_section); // navigation rakhna pare yesh ma r.layout.activity_nav_drawer_layout rakhnu
        getSupportActionBar().setTitle("Safa Samaj");
        firebaseAuth= FirebaseAuth.getInstance();


/*

        navigation drawer ko code till toggle.syncState()
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
*/

        button_my_profile = findViewById(R.id.buttonmyprofile);
        button_organic_waste = findViewById(R.id.buttonOrganicWaste);
        button_my_schedule = findViewById(R.id.buttonmyschedule);
        button_my_location = findViewById(R.id.buttonmylocation);
        button_my_notifications = findViewById(R.id.buttonnotifications);
        button_about_app = findViewById(R.id.butonAboutTheAPP);
        button_project_team = findViewById(R.id.buttonProjectTeam);
        button_acknowledgement = findViewById(R.id.butonAcknowledgement);
        button_signout = findViewById(R.id.buttonSignOut);


        button_my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "My Profile ", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(Guide_section.this, profileUser.class);
                 startActivity(intent);
            }
        });
        button_organic_waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "Organic Waste ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Guide_section.this, Organic_Waste.class);
                startActivity(intent);
            }
        });

        button_my_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "My Schedule ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Guide_section.this, mySchedule.class);
                startActivity(intent);
            }
        });
        button_my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "My Location ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Guide_section.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button_my_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "My Notifications ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Guide_section.this, mainNotification.class);
                startActivity(intent);
            }
        });

        button_about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "About the APP ", Toast.LENGTH_SHORT).show();
            }
        });
        button_project_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "Project Teams ", Toast.LENGTH_SHORT).show();
            }
        });
        button_acknowledgement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Guide_section.this, "Acknowledgements ", Toast.LENGTH_SHORT).show();
            }
        });
        button_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Guide_section.this,FrontPage.class));
                Toast.makeText(Guide_section.this, "SignOut ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    /*navigation bar ko code
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }*/
}
