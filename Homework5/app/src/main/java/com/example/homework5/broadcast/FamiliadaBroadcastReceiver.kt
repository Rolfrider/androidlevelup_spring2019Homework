package com.example.homework5.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.homework5.NotificationFactory

class FamiliadaBroadcastReceiver: BroadcastReceiver() {

    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationFactory.show(context!!, "Familiada o 16:35 na TVP2",
            "Nie przegap najlepszego programu rozrywkowego")
    }
}