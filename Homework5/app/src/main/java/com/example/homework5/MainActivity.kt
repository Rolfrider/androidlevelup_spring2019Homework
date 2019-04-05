package com.example.homework5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleSingleWork()
        scan_button.setOnClickListener(this::handleScanButton)

    }

    private fun handleScanButton(view: View){
        NotificationFactory().show(this, "test", "test")
    }



    private fun scheduleSingleWork() {
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
