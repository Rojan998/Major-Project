package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class recycler_acknowledgement extends AppCompatActivity {

    RecyclerView mRecyclerView_ack;
    adapter_acknowledgement mAdapter_notification_ack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_acknowledgement);
        getSupportActionBar().setTitle("Acknowledgements");


        mRecyclerView_ack = findViewById(R.id.acknowledgementRecycler);
        mRecyclerView_ack.setLayoutManager(new LinearLayoutManager(this));


        mAdapter_notification_ack = new adapter_acknowledgement(this,getMyList_ack());
        mRecyclerView_ack.setAdapter(mAdapter_notification_ack);
    }

    private ArrayList<model_acknowledgement> getMyList_ack(){
        ArrayList<model_acknowledgement> models_ack = new ArrayList<>();


        model_acknowledgement m = new model_acknowledgement();
        m.setMessage_ack("Sudeep Shakya");
        m.setMessageDescription_ack("HOD Computer Science AND Supervisor");
        m.setImg_ack(R.drawable.sudeep_image);
        models_ack.add(m);


        m = new model_acknowledgement();
        m.setMessage_ack("Sapana Thakulla");
        m.setMessageDescription_ack("Project Coordinator");
        m.setImg_ack(R.drawable.sapana_image);
        models_ack.add(m);

        m = new model_acknowledgement();
        m.setMessage_ack("KEC");
        m.setMessageDescription_ack("BCT B");
        m.setImg_ack(R.drawable.kec_image);
        models_ack.add(m);


        return models_ack;
    }
}