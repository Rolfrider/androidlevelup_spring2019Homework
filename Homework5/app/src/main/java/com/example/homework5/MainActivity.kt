package com.example.homework5

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.*
import com.example.homework5.broadcast.FamiliadaBroadcastReceiver
import com.example.homework5.service.ScanningForegroundService
import com.example.homework5.worker.CreepWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private val alarmManager: AlarmManager
        get() = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleFamiliadaAlarm()
        scheduleObserveWork()
        startScanningService()
        scan_button.setOnClickListener(this::handleScanButton)
        stopScan_button.setOnClickListener(this::handleStopScanButton)

    }

    private fun handleScanButton(view: View){
        startScanningService()
    }

    private fun handleStopScanButton(view: View){
        stopScanningService()
    }

    private fun startScanningService(){
        ContextCompat.startForegroundService(this, Intent(this, ScanningForegroundService::class.java))
    }

    private fun stopScanningService(){
        stopService(Intent(this, ScanningForegroundService::class.java))
    }


    private fun scheduleObserveWork(){
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = OneTimeWorkRequest.Builder(CreepWorker::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance()
            .enqueueUniqueWork(CreepWorker::javaClass.name, ExistingWorkPolicy.APPEND, request)// TODO: Change to replace

    }


    private fun scheduleFamiliadaAlarm() {

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 14) // TODO: ustawić 16.20
            set(Calendar.MINUTE, 50)
        }

        Intent(this, FamiliadaBroadcastReceiver::class.java)
            .apply { action = "com.example.homework5.NOTIFY" }
            .let { PendingIntent.getBroadcast(this, 0, it, 0) }
            .let {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it)
            }
    }
}
