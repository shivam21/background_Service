package com.bhusridevelopers.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.R
import android.app.Notification
import androidx.core.app.NotificationCompat


class TemporaryService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private val NOTIFICATION_ID = 1001
    private lateinit var notification: Notification

    override fun onCreate() {
        super.onCreate()
        notification = NotificationCompat.Builder(this, "channel_01")
            .setSmallIcon(R.drawable.sym_def_app_icon).setContentTitle("Test")
            .setContentText("Checking messages...").build()
        startForeground(NOTIFICATION_ID, notification)
        startService(Intent(this, PermanentService::class.java))
//        stopForeground(true)
//        stopSelf()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

}