package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class recycler_notification extends AppCompatActivity {

    RecyclerView mRecyclerView;
    adapter_notification mAdapter_notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_notification);
        getSupportActionBar().setTitle("Project Team");

        mRecyclerView = findViewById(R.id.notificationRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter_notification = new adapter_notification(this,getMyList());
        mRecyclerView.setAdapter(mAdapter_notification);


    }

    private ArrayList<model_notification> getMyList(){
        ArrayList<model_notification> models = new ArrayList<>();


        model_notification m = new model_notification();
        m.setMessage("Ojaswee Kafle");
        m.setMessageDescription("BCT B 73061");
        m.setImg(R.drawable.ojaswee_image_3);
        models.add(m);


        m = new model_notification();
        m.setMessage("Rojan Adhikari");
        m.setMessageDescription("BCT B 73061");
        m.setImg(R.drawable.rojan_image_1);
        models.add(m);

        m = new model_notification();
        m.setMessage("Suhana Pradhan");
        m.setMessageDescription("BCT B 73082");
        m.setImg(R.drawable.suhana_image1);
        models.add(m);

//        m = new model_notification();
//        m.setMessage("Message 4");
//        m.setMessageDescription("This is mesasge Description 4");
//        m.setImg(R.drawable.acknowledgement);
//        models.add(m);

        return models;
    }
}