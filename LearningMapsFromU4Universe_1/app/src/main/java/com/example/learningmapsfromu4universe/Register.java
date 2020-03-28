package com.example.learningmapsfromu4universe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.PriorityQueue;

public class Register extends AppCompatActivity {

    private EditText txt_name;
    private EditText txt_address;
    private EditText txt_email;
    private EditText txt_password;
    private EditText txt_confirmPassword;
    private EditText txt_familyMembers;
    private EditText txt_houseNumber;
    private EditText txt_phoneNumber;

    private Button registerBtn;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Signup for Users");

        txt_name = findViewById(R.id.Name);
        txt_address = findViewById(R.id.Address);
        txt_email = findViewById(R.id.Email);
        txt_password = findViewById(R.id.Password);
        txt_confirmPassword = findViewById(R.id.ConfirmPassword);
        txt_familyMembers = findViewById(R.id.FamilyNumber);
        txt_houseNumber = findViewById(R.id.HouseNumber);
        txt_phoneNumber = findViewById(R.id.PhoneNumber);

        registerBtn = findViewById(R.id.Register);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txt_name.getText().toString().trim();
                String address = txt_address.getText().toString().trim();
                String house_number = txt_houseNumber.getText().toString().trim();
                String phone_number = txt_phoneNumber.getText().toString().trim();
                String family_members = txt_familyMembers.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String password = txt_password.getText().toString().trim();
                String confirmPassword = txt_confirmPassword.getText().toString().trim();

                email = "U_" + email;

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Please Enter Email !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(Register.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(Register.this, "Password too Short ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Registering....", Toast.LENGTH_SHORT).show();
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {

                                        String name = txt_name.getText().toString().trim();
                                        String address = txt_address.getText().toString().trim();
                                        String house_number = txt_houseNumber.getText().toString().trim();
                                        String phone_number = txt_phoneNumber.getText().toString().trim();
                                        String family_members = txt_familyMembers.getText().toString().trim();
                                        String email = txt_email.getText().toString().trim();
                                        String password = txt_password.getText().toString().trim();


                                        userRegistrationHelper helpUser = new userRegistrationHelper(

                                                name,
                                                address,
                                                email,
                                                password,
                                                family_members,
                                                house_number,
                                                phone_number

                                        );

                                        email = txt_email.getText().toString().trim();
                                        email = "U_" + email;
                                        name = txt_name.getText().toString().trim();

                               /*       int iend = email.indexOf(".");
                                        String subEmail = "null";
                                        if (iend != -1) {
                                            subEmail = email.substring(0, iend);
                                        }
                                        DatabaseReference databaseReference;*/

                                        /*databaseReference =*/
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(helpUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(Register.this, "Registration complete", Toast.LENGTH_SHORT).show();

                                                Intent i = new Intent(Register.this, FrontPage.class);
                                                startActivity(i);
                                            }

                                        });
                                        /* databaseReference.child(subEmail).setValue(name);*/
/*
                                        Intent i = new Intent(Register.this, FrontPage.class);
                                        startActivity(i);*/
                                    } else {
                                        Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(Register.this, "Password do not match!", Toast.LENGTH_SHORT).show();
                }

            }


        });


    /*    registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(Register.this, "Registration in progress .... ", Toast.LENGTH_SHORT).show();

                final String Name = name.getText().toString();
                final String Address = address.getText().toString();
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();
                final String NoOfFamilyMembers = familyMembers.getText().toString();
                final String HouseNumber = houseNumber.getText().toString();
                final String PhoneNumber = phoneNumber.getText().toString();
                final String ConfirmPassword = confirmPassword.getText().toString();

                Email = "U" + Email;


                if (TextUtils.isEmpty((CharSequence) Email)) {
                    Toast.makeText(Register.this, "Please enter Email ! ", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty((CharSequence) Password)) {
                    Toast.makeText(Register.this, "Please enter Password ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) ConfirmPassword)) {
                    Toast.makeText(Register.this, "Please enter Valid Password ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) Name)) {
                    Toast.makeText(Register.this, "Please enter Name ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) Address)) {
                    Toast.makeText(Register.this, "Please enter Address ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) NoOfFamilyMembers)) {
                    Toast.makeText(Register.this, "Please enter Number of Family Members ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) HouseNumber)) {
                    Toast.makeText(Register.this, "Please enter the House Number ! ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty((CharSequence) PhoneNumber)) {
                    Toast.makeText(Register.this, "Please enter Phone Number ! ", Toast.LENGTH_SHORT).show();
                }

                if (Password.length() < 6) {
                    Toast.makeText(Register.this, "Password too Short ! ", Toast.LENGTH_SHORT).show();
                }


                if (Password.equals(ConfirmPassword)) {
                    Toast.makeText(Register.this,"Registering....",Toast.LENGTH_SHORT).show();

                    firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        userRegistrationHelper helpUser = new userRegistrationHelper(

                                                Name,
                                                Address,
                                                Email,
                                                Password,
                                                NoOfFamilyMembers,
                                                HouseNumber,
                                                PhoneNumber
                                        );


                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(helpUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Register.this, "Registration Complete !", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), FrontPage.class));


                                            }
                                        });


                                    } else {
                                        Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });


                }else{
                    Toast.makeText(Register.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

    }

}
