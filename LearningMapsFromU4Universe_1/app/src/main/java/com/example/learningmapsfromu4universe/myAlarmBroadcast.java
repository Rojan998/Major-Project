package com.example.learningmapsfromu4universe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class myAlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context,
                Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.start();
    }
}
