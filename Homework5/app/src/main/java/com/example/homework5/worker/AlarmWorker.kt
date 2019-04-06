package com.example.homework5.worker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.homework5.broadcast.FamiliadaBroadcastReceiver
import java.util.*

class AlarmWorker(val context: Context, params: WorkerParameters): Worker(context, params) {

    private val alarmManager: AlarmManager
        get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


    private val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 16)
        set(Calendar.MINUTE, 20)
    }

    override fun doWork(): Result {
        Handler(Looper.getMainLooper()).post {
            scheduleFamiliadaAlarm()
        }
        return Result.success()
    }


    private fun scheduleFamiliadaAlarm() {
        Intent(context, FamiliadaBroadcastReceiver::class.java)
            .apply { action = "com.example.homework5.NOTIFY" }
            .let { PendingIntent.getBroadcast(context, 0, it, 0) }
            .let {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it)
            }
    }
}