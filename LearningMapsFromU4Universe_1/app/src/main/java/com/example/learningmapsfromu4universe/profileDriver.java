package com.example.learningmapsfromu4universe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileDriver extends AppCompatActivity {

    TextView txt_drivername, txt_address, txt_email, txt_password, txt_license_number, txt_phone_number;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_driver);
        getSupportActionBar().setTitle("My Profile");

        txt_drivername = findViewById(R.id.profile_driver_name);
        txt_address =findViewById(R.id.profile_driver_address);
        txt_email = findViewById(R.id.profile_driver_email);
        txt_password = findViewById(R.id.profile_driver_password);
        txt_license_number = findViewById(R.id.profile_driver_license_number);

        txt_phone_number =findViewById(R.id.profile_driver_phone_number);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Drivers");
        FirebaseDatabase.getInstance().getReference("Drivers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String driver_name = dataSnapshot.child("DriverName").getValue().toString();
                        String address = dataSnapshot.child("Address").getValue().toString();
                        String email = dataSnapshot.child("Email").getValue().toString();
                        String password = dataSnapshot.child("Password").getValue().toString();
                        String license_number = dataSnapshot.child("LicenseNumber").getValue().toString();

                        String phone_number = dataSnapshot.child("PhoneNumber").getValue().toString();

                        txt_drivername.setText(driver_name);
                        txt_address.setText(address);
                        txt_email.setText(email);
                        txt_password.setText(password);
                        txt_license_number.setText(license_number);

                        txt_phone_number.setText(phone_number);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
