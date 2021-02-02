package com.organicpy.myapplication.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.organicpy.myapplication.R;

import java.util.Map;

/**
 * Used for handling Push Notifications sent by firebase cloud messaging,
 * with custom notification feature, both working for api and firebase messaging
 *
 * @author Mohd Hussain
 * @version 1.0
 * @since 15-01-2021
 */
public class PushNotificationService extends FirebaseMessagingService {
    private String tag = "FireTag";

    @Override
    public void onNewToken(@NonNull String s) {
        Log.d(tag, "Refreshed token: " + s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(tag, "onMessageReceived: " + remoteMessage.getData().get("title"));
        Map<String, String> data = remoteMessage.getData();
        customNotifications(data.get("title"), data.get("body"));
    }

    /**
     * Custom Notification method - Provide customizable notification for the user, added all the work
     * required to run notification when received
     *
     * Known issues: custom notification layout does not work with parent constraint layout, should be linearLayout
     *
     * @param title - title of the notification
     * @param body - message of the notification
     */
    void customNotifications(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        // Only required for 10 or up android level
        NotificationChannel channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(getString(R.string.channel_id), getString(R.string.channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        // RemoteView to get custom layout
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
        // Expandable layout
        RemoteViews expandedNotificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification_expanded);

        // setting up custom image, title and the body for notification
        notificationLayout.setTextViewText(R.id.title_text, title);
        notificationLayout.setTextViewText(R.id.body_text, body);
        notificationLayout.setImageViewResource(R.id.notification_image, R.drawable.notification);

        // expanded setting
        expandedNotificationLayout.setTextViewText(R.id.expanded_notification_title, title);
        expandedNotificationLayout.setTextViewText(R.id.expanded_notification_body, body);

        // Notification builder with custom build functionality
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.channel_id))
                .setSmallIcon(R.drawable.notification)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(expandedNotificationLayout)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(R.string.notification_id, builder.build());
    }
}
