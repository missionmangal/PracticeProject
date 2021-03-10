package com.myapplication.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.myapplication.R

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        showNotification(context, "Alarm Reminder ", "Wake up", intent)
    }

    fun showNotification(
            context: Context?,
            title: String?,
            body: String?,
            intent: Intent?
    ) {
        var eventsChannel: NotificationChannel? = null
        var notificationBuilder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            eventsChannel = NotificationChannel(
                    All_CHANNEL_ID,
                    All_CHANNEL,
                    NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(eventsChannel)
            notificationBuilder = NotificationCompat.Builder(
                    context,
                    All_CHANNEL_ID
            )
        } else {
            notificationBuilder = NotificationCompat.Builder(
                    context!!,
                    All_CHANNEL_ID
            )
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val defaultSoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        notificationBuilder //                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle(title + "")
                .setContentText(body + "")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(
                        NotificationCompat.BigTextStyle()
                                .bigText(body + "")
                )
                .setContentIntent(pendingIntent)

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)

        notificationBuilder.color = context?.resources
                ?.getColor(R.color.colorPrimary) ?: 0
        val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
                System.currentTimeMillis().toInt(),
                notificationBuilder.build()
        )
    }

    companion object {
        private const val TAG = "FCM Message"
        private const val All_CHANNEL_ID = "Maxillofiacia"
        private const val All_CHANNEL = "Maxillofiacia"
    }
}