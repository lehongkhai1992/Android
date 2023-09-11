package com.example.s18androidserviceandbroadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask


class MyBroadcastReciverService : Service() {

    companion object {
        const val CURRENT_TIME = "current time"
        const val UPDATE_TIME = "update time"
    }

    private var timer = Timer()
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.scheduleAtFixedRate(StopWatchTimeTask(time), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class StopWatchTimeTask(private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(UPDATE_TIME)
            time++
            intent.putExtra(CURRENT_TIME, time)
            sendBroadcast(intent)
        }

    }

}
