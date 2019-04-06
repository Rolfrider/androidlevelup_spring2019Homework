package com.example.homework5.worker

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.homework5.NotificationFactory

class CreepWorker(private val context: Context, parameters: WorkerParameters):
    Worker(context,parameters) {

    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }

    override fun doWork(): Result {
        Handler(Looper.getMainLooper()).post {
            notificationFactory.show(context, "Obserwujemy Cię!", "Ładowarka podłączona :)")
        }
        return Result.success()
    }
}