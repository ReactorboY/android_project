package com.organicpy.myapplicationkt.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.organicpy.myapplicationkt.R

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 17-01-2021
 */
class PushNotificationService : FirebaseMessagingService() {

    private val TAG = "FireTag"

     override fun onNewToken(s: String) {
        super.onNewToken(s)
         Log.d(TAG, "onNewToken: $s")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        createNotification(remoteMessage.notification?.title!!, remoteMessage.notification?.body!!)
    }

    private fun createNotification(title: String, body : String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(getString(R.string.channel_id), getString(R.string.channel_name), importance)

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)) {
            notify(R.string.unique_notification_id, builder.build())
        }
    }
}