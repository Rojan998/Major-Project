package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.nio.file.WatchEvent;

public class WelcomeDrivers extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_drivers);
        getSupportActionBar().setTitle("Safa Samaj");
        firebaseAuth= FirebaseAuth.getInstance();

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
                Toast.makeText(WelcomeDrivers.this, "My Profile ", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(WelcomeDrivers.this, profileDriver.class);
                 startActivity(intent);
            }
        });
        button_organic_waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "Organic Waste ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeDrivers.this, Organic_Waste.class);
                startActivity(intent);
            }
        });

        button_my_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "My Schedule ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeDrivers.this, mySchedule.class);
                startActivity(intent);
            }
        });
        button_my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "My Location ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeDrivers.this, DriverMapping.class);
                startActivity(intent);
            }
        });
        button_my_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(WelcomeDrivers.this, "My Notifications ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(WelcomeDrivers.this, m1_receive_notification.class);
                startActivity(intent);
            }
        });

        button_about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "About the APP ", Toast.LENGTH_SHORT).show();
            }
        });
        button_project_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "Project Teams ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeDrivers.this, recycler_notification.class);
                startActivity(intent);
            }
        });
        button_acknowledgement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeDrivers.this, "Acknowledgements ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeDrivers.this, recycler_acknowledgement.class);
                startActivity(intent);
            }
        });
        button_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(WelcomeDrivers.this,FrontPage.class));
                Toast.makeText(WelcomeDrivers.this, "SignOut ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
