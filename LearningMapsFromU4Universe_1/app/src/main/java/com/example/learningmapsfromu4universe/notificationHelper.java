package com.example.learningmapsfromu4universe;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.learningmapsfromu4universe.mainNotification.CHANNEL_ID;

public class notificationHelper {


    public static void  displayNotification(Context context, String title, String body) {


        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                100,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );


        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(context, mainNotification.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, mbuilder.build());

    }
}
