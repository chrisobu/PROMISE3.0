package com.example.faars.promise30.Notifications.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import android.util.Log;

import com.example.faars.promise30.LogInActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.Notifications.Receivers.NotificationEventReceiver;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.util.List;


public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_CHECK_IF_CHILDREN_REQUIRES_VIDEO_RECORDING = "ACTION_CHECK_CHILDREN_VIDEO";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_CHECK_IF_CHILDREN_REQUIRES_VIDEO_RECORDING);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_CHECK_IF_CHILDREN_REQUIRES_VIDEO_RECORDING.equals(action)) {
                processStartNotification();
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification() {
        MyDBHandler dbHandler = MyDBHandler.getInstance(this);
        List<String> children =dbHandler.getChildrenWhichRequiresVideoRecording();
        Log.i("SIRI", String.format("Found %s children that wants video recording attention", children.size()));
        if(children.size() > 0){
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("PROMISE")
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setContentText("Record a video of: " + TextUtils.join(", ", children) + " and send")
                    .setSmallIcon(R.drawable.barn);

            Intent mainIntent = new Intent(this, LogInActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    NOTIFICATION_ID,
                    mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

            final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, builder.build());
        }

    }
}
