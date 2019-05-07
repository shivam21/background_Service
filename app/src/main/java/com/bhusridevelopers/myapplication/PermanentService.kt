package com.bhusridevelopers.myapplication

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log


class PermanentService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    var time = 0

    private val keepAliveRunnable = object : Runnable {
        override fun run() {
            Log.d("PermanentService", "$time")
            time++
            //keepServiceAlive()
            if (handler != null) handler!!.postDelayed(this, 15 * 1000)
        }
    }
    private var handler: Handler? = null

    override fun onCreate() {
        time = 0
        handler = Handler()
        handler!!.postDelayed(keepAliveRunnable, 30 * 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        keepServiceAlive()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        keepServiceAlive()
    }

    private fun keepServiceAlive() {
        handler?.removeCallbacks(keepAliveRunnable)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this@PermanentService, TemporaryService::class.java))
        } else {
            startService(Intent(this@PermanentService, TemporaryService::class.java))
        }
    }
}