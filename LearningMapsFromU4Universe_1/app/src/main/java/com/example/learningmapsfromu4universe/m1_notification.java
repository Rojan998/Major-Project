package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class m1_notification extends AppCompatActivity {
    Button sendNotification;
    private RequestQueue mRequestQueue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    private String ServerURL = "AAAARp5VyH4:APA91bEH99a36scmq8JCetaqEWBQMBpop12CgVQZ6LFYVFVOpxuFhiE44CJq-bKIjmZ5HLpBsDVPTIsQuTH9sGUuTB9nBKENxgz-wF-CsEGesv-UW36sVae9gbgKTfKINgPeNOnG7Cj6";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_notification);
        getSupportActionBar().setTitle("Notifications");


        if (getIntent().hasExtra("category")){
            Intent intent = new Intent(m1_notification.this,m1_receive_notification.class);
            intent.putExtra("category",getIntent().getStringExtra("category"));
            intent.putExtra("brandId",getIntent().getStringExtra("brandId"));
            startActivity(intent);
        }

        sendNotification = findViewById(R.id.sendNotification);
        mRequestQueue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotificationFunction();
            }
        });
    }

    private void sendNotificationFunction(){
        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","Late Arrival");
            notificationObj.put("body","You have been late");

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","Late Arrivalls");
            extraData.put("category","Late mate");


            json.put("notification",notificationObj);
            json.put("data",extraData);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("MUR", "onResponse: ");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }

            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Content-Type","application/json");
                    header.put("Authorization", "key=AAAARp5VyH4:APA91bEH99a36scmq8JCetaqEWBQMBpop12CgVQZ6LFYVFVOpxuFhiE44CJq-bKIjmZ5HLpBsDVPTIsQuTH9sGUuTB9nBKENxgz-wF-CsEGesv-UW36sVae9gbgKTfKINgPeNOnG7Cj6");
                    return header;
                }
            };

            mRequestQueue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}