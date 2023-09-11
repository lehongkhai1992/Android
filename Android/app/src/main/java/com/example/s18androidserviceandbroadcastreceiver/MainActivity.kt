package com.example.s18androidserviceandbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.s18androidserviceandbroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private lateinit var intentService: Intent
    private var time: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartOrStop.setOnClickListener {
            btnStartOrStop()
        }

        intentService = Intent(applicationContext, MyBroadcastReciverService::class.java)
        registerReceiver(stopWatchBroadcast, IntentFilter(MyBroadcastReciverService.UPDATE_TIME))

    }

    private fun btnStartOrStop() {
        if (isStarted) {
            stop()
        } else {
            start()
        }
    }

    private fun stop() {
        binding.btnStartOrStop.text = "start"
        isStarted = false
        stopService(intentService)
    }

    private fun start() {
        binding.btnStartOrStop.text = "stop"
        isStarted = true
        intentService.putExtra(MyBroadcastReciverService.CURRENT_TIME, time)
        startService(intentService)
    }

    private val stopWatchBroadcast : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(MyBroadcastReciverService.CURRENT_TIME, 0.0)
            binding.txtStopWatch.text = time.toString()
        }

    }
}