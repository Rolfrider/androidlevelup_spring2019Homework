package com.example.homework5.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.homework5.NotificationFactory

class GreetingsBroadcastReceiver: BroadcastReceiver() {

    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }

    override fun onReceive(context: Context?, intent: Intent?) {

        when(intent?.action){
            Intent.ACTION_BOOT_COMPLETED -> {
                notificationFactory.show(context!!, "Witaj ponownie!",
                    "Cieszymy się, że znowu jesteś z nami")
            }
        }

    }
}