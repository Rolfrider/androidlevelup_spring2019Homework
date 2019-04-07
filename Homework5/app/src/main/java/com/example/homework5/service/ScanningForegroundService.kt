package com.example.homework5.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.homework5.NotificationFactory

private const val NOTIFICCATION_ID = 999

class ScanningForegroundService: Service() {

    private val notificationFactory by lazy { NotificationFactory() }

    override fun onCreate() {
        super.onCreate()
        startForeground(
            NOTIFICCATION_ID, notificationFactory.create(this, "Skanowanie",
            "Skanowanie beconów aktywne..."))

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}