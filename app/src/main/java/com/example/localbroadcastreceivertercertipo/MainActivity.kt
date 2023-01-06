package com.example.localbroadcastreceivertercertipo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    val myReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.action == "CustomReceiver"){
                Log.d("Receiver", "CustomReceiver recibido")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.btn_receiver)

        button.setOnClickListener {

            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("CustomReceiver"))

        }
    }

    override fun onResume() {
        super.onResume()

        doLocalReceiver()
    }

    private fun doLocalReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, IntentFilter("CustomReceiver"))
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
    }

}