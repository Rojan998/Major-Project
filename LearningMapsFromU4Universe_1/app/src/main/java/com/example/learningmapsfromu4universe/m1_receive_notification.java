package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class m1_receive_notification extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_receive_notification);
        getSupportActionBar().setTitle("My Notifications");


        TextView categoryTv = findViewById(R.id.message_title);
        //TextView brandTv = findViewById(R.id.message_description);



        if (getIntent().hasExtra("category")){
            String category = getIntent().getStringExtra("category");
            String brand = getIntent().getStringExtra("brandId");
            categoryTv.setText(category);
            //brandTv.setText(brand);
        }
    }
}