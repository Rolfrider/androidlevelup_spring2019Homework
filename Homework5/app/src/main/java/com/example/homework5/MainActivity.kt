package com.example.homework5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.*
import com.example.homework5.service.ScanningForegroundService
import com.example.homework5.worker.AlarmWorker
import com.example.homework5.worker.CreepWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleAlarmWork()
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
            .enqueueUniqueWork(CreepWorker::javaClass.name, ExistingWorkPolicy.APPEND, request)

    }

    private fun scheduleAlarmWork() {
        val constraints = Constraints.Builder()
            .build()
        val request = OneTimeWorkRequest.Builder(AlarmWorker::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager
            .getInstance()
            .enqueueUniqueWork(AlarmWorker::javaClass.name, ExistingWorkPolicy.REPLACE, request)
    }
}
