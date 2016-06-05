package com.example.faars.promise30.Notifications.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NotificationServiceStarterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationEventReceiver.setupAlarm(context);
    }
}
