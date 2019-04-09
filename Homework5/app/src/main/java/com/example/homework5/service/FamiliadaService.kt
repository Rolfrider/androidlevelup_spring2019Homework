package com.example.homework5.service

import android.app.IntentService
import android.content.Intent
import com.example.homework5.NotificationFactory

class FamiliadaService: IntentService("FamiliadaService") {

    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }

    override fun onHandleIntent(intent: Intent?) {
        notificationFactory.show(this, "Familiada o 16:35 na TVP2",
            "Nie przegap najlepszego programu rozrywkowego")
    }

}