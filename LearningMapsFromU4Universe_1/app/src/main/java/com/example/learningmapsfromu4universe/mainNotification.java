package com.example.learningmapsfromu4universe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class mainNotification extends AppCompatActivity {

    // Notification Channel
    //Notification Builder
    //Notification Manager

    public static final String CHANNEL_ID = "Notification";
    public static final String CHANNEL_NAME = "User Location";
    public static final String CHANNEL_DESC = "User Location Details";

    private TextView show_token;

    FirebaseAuth firebaseAuth;
    // FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

   // Button btn_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users Token");

   /*     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }*/
  /*      btn_button = findViewById(R.id.textview_token);

        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });*/


        show_token = findViewById(R.id.textview_token);



        //subscribing to the topic

        FirebaseMessaging.getInstance().subscribeToTopic("Delay");

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            saveToken(token);
                            //show_token.setText(token);


                        } else {
                            show_token.setText(task.getException().getMessage());


                        }
                    }
                });
    }

    private void saveToken(String token){

        String user_email = firebaseAuth.getCurrentUser().getEmail();

        userToken  user_Token = new userToken(user_email, token);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users Token");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                .setValue(user_Token).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                Toast.makeText(mainNotification.this,"Token Saved",Toast.LENGTH_LONG).show();
            }
            }
        });




    }


}
