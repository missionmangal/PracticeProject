package com.myapplication.alarm

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FCM Message"
        private const val All_CHANNEL_ID = "Maxillofiacia"
        private const val All_CHANNEL = "Maxillofiacia"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        btnSetAlarm.setOnClickListener {
            Toast.makeText(this@AlarmActivity, "Alarm set",Toast.LENGTH_LONG).show()
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val clickTime = System.currentTimeMillis()
            val tenSecond = 10*1000
            val intent = Intent(this , ReminderBroadcast::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getBroadcast(
                    this, 0, intent,
                   0
            )
//            showNotification("Alarm Reminder ", "Wake up",pendingIntent)
            alarmManager.set(AlarmManager.RTC_WAKEUP,clickTime+tenSecond,pendingIntent)
        }
    }

    fun createNotificationBuilder(){
        val intent = Intent(this, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this , "Test")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm Reminder")
                .setContentText("Wake up")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }

    fun createNotificationChannel() {
        var channel : NotificationChannel ?=null
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){

            channel = NotificationChannel("1","Test",NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
//        return channel
    }






}