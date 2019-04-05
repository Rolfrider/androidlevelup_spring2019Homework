package com.example.homework5

import android.app.Application
import android.os.Build

class HomeworkApp: Application() {
    private val notificationFactory by lazy { NotificationFactory() }

    override fun onCreate() {
        super.onCreate()
        initNotificationChannels()
    }

    private fun initNotificationChannels() {
        fromAndroid(Build.VERSION_CODES.O) { notificationFactory.createNotificationChannels(this) }
    }
}