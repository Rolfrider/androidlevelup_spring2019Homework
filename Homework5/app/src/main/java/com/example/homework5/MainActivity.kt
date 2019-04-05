package com.example.homework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scan_button.setOnClickListener(this::handleScanButton)
    }

    private fun handleScanButton(view: View){
        NotificationFactory().show(this, "test", "test")
    }
}
