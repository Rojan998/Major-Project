package com.example.learningmapsfromu4universe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.sql.Driver;
import java.util.Timer;
import java.util.TimerTask;

public class FrontPage extends AppCompatActivity {

    EditText txt_Email, txt_Password;
    private Button Login;
    private Button RegisterTo;

    private FirebaseAuth firebaseAuth;

    ViewPager viewPager;
    CustomSwipeAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        getSupportActionBar().setTitle("Welcome ! ");

        viewPager = findViewById(R.id.view_pager);
        adapter =new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),1000,2000);





        Login = findViewById(R.id.login);
        RegisterTo = findViewById(R.id.FrontRegister);
        txt_Email = findViewById(R.id.loginEmail);
        txt_Password = findViewById(R.id.loginPassword);

        firebaseAuth =FirebaseAuth.getInstance();

        RegisterTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(FrontPage.this, "Registration....", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FrontPage.this, Register.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Email1 = txt_Email.getText().toString();
                String Password = txt_Password.getText().toString();
                String Email = "a";

                if (TextUtils.isEmpty((CharSequence) Email1)) {
                    Toast.makeText(FrontPage.this, "Please enter Email ! ", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty((CharSequence) Password)) {
                    Toast.makeText(FrontPage.this, "Please enter Valid Password ! ", Toast.LENGTH_SHORT).show();
                }

                if (Password.length() < 6) {
                    Toast.makeText(FrontPage.this, "Password too Short ! ", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(FrontPage.this, "Login in progress...", Toast.LENGTH_SHORT).show();
                    Email= "D_" + Email1;

                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(FrontPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    String Email1 = txt_Email.getText().toString().trim();
                                   // String Password = txt_Password.getText().toString().trim();
                                    String Email = "D_" + Email1;
                                    Intent intent = new Intent(FrontPage.this, WelcomeDrivers.class).putExtra("email", Email);
                                    startActivity(intent);
                                   /* Intent intent = new Intent(FrontPage.this, Guide_section.class);
                                    startActivity(intent);*/

                                    //startActivity(new Intent(getApplicationContext(), Guide_section.class));
                                } else {
                                    String Email1 = txt_Email.getText().toString().trim();
                                    String Password = txt_Password.getText().toString().trim();
                                    String Email = "U_" + Email1;
                                    //Toast.makeText(FrontPage.this, "Login Failed.... Please Register before Logging in ! ", Toast.LENGTH_SHORT).show();
                                    firebaseAuth.signInWithEmailAndPassword(Email, Password)
                                            .addOnCompleteListener(FrontPage.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        String email1 = txt_Email.getText().toString().trim();
                                                        String email = "U_" + email1;
                                                        Intent intent = new Intent(FrontPage.this, Guide_section.class).putExtra("email", email);
                                                        startActivity(intent);
                                                    } else {
                                                        String email1 = txt_Email.getText().toString().trim();
                                                        String password = txt_Password.getText().toString().trim();
                                                        String email = "A_" + email1;
                                                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                                                .addOnCompleteListener(FrontPage.this, new OnCompleteListener<AuthResult>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                        if (task.isSuccessful()) {
                                                                            Intent intent = new Intent(FrontPage.this, admin.class);
                                                                            startActivity(intent);
                                                                        } else {
                                                                            Toast.makeText(FrontPage.this, "Login Failed or USER NOT REGISTERED !", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });


                                                    }
                                                }
                                            });

                                }

                                //Toast.makeText(FrontPage.this, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                            }

                        });

                Toast.makeText(FrontPage.this, "Logging in ..... !", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(FrontPage.this, Login.class);
            //    startActivity(intent);
            }

        });




    }

public class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        FrontPage.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (viewPager.getCurrentItem()== 0){
                    viewPager.setCurrentItem(1);

                }else if (viewPager.getCurrentItem()==1){
                    viewPager.setCurrentItem(2);

                }else if (viewPager.getCurrentItem()==2) {
                    viewPager.setCurrentItem(3);
                }else if (viewPager.getCurrentItem()==3) {
                    viewPager.setCurrentItem(0);
                }else {
                    viewPager.setCurrentItem(0);
                }
            }
        });
    }
}
}
