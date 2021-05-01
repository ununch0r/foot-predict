package com.example.footpredict

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import com.example.footpredict.helpers.NotificationPublisher


class MainActivity : Activity() {
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    lateinit var builder: Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var newButton = findViewById<Button>(R.id.btnNew)
        var savedButton = findViewById<Button>(R.id.btnSaved)

        newButton.setOnClickListener{
            val intent = Intent(this,LeaguesActivity::class.java)
            startActivity(intent)
        }

        savedButton.setOnClickListener{
//            val intent = Intent(this,SavedMatchesActivity::class.java)
//            startActivity(intent)

            scheduleNotification(getNotification("tesssst"), 10000)
        }
    }

    private fun scheduleNotification(notification: Notification?, delay: Int) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis] = pendingIntent
    }


    private fun getNotification(content: String): Notification? {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, content, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
            builder.setContentTitle("Scheduled Notification");
            builder.setContentText(content);
            builder.setSmallIcon(R.drawable.ic_launcher_background);
        } else {
            builder = Notification.Builder(this)
            builder.setContentTitle("Scheduled Notification");
            builder.setContentText(content);
            builder.setSmallIcon(R.drawable.ic_launcher_background)
        }

        return builder.build()
    }
}