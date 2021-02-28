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

public class profileUser extends AppCompatActivity {

    TextView txt_username, txt_address, txt_email, txt_password, txt_family_members, txt_house_number, txt_phone_number;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        getSupportActionBar().setTitle("My Profile");

        txt_username = findViewById(R.id.profile_user_name);
        txt_address =findViewById(R.id.profile_user_address);
        txt_email = findViewById(R.id.profile_user_email);
        txt_password = findViewById(R.id.profile_user_password);
        txt_family_members = findViewById(R.id.profile_user_family_members);
        txt_house_number = findViewById(R.id.profile_user_house_number);
        txt_phone_number =findViewById(R.id.profile_user_phone_number);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String username = dataSnapshot.child("UserName").getValue().toString();
                        String address = dataSnapshot.child("Address").getValue().toString();
                        String email = dataSnapshot.child("Email").getValue().toString();
                        String password = dataSnapshot.child("Password").getValue().toString();
                        String family_member = dataSnapshot.child("FamilyMembers").getValue().toString();
                        String house_number = dataSnapshot.child("HouseNumber").getValue().toString();
                        String phone_number = dataSnapshot.child("PhoneNumber").getValue().toString();

                        txt_username.setText(username);
                        txt_address.setText(address);
                        txt_email.setText(email);
                        txt_password.setText(password);
                        txt_family_members.setText(family_member);
                        txt_house_number.setText(house_number);
                        txt_phone_number.setText(phone_number);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
