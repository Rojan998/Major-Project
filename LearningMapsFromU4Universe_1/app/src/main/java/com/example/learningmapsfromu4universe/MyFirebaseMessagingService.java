package com.example.learningmapsfromu4universe;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        Map<String,String> extraData = remoteMessage.getData();

        String brandId = extraData.get("brandId");
        String category = extraData.get("category");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "WasteNotify")
                .setContentTitle(title).setContentText(body).setSmallIcon(R.drawable.notifications);

        Intent intent;
        if (category.equals("You have been late for pickup")){
            intent = new Intent(this,m1_receive_notification.class);

        }
        else {
            intent = new Intent(this,m1_receive_notification.class);
        }
        intent.putExtra("brandID", brandId);
        intent.putExtra("category",category);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int id= (int) System.currentTimeMillis();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("WasteNotify","demo",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(id,notificationBuilder.build());


    }


}
