package com.services.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread
import kotlin.math.truncate

class LoggerService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d("ServiceTag", "onCreate: Service Create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ServiceTag", "onCreate: Service Start")
        thread (start = true){
            while (true){
                Thread.sleep(1000)
                Log.d("ServiceTag", "onCreate: Service is Running")
            }
        }
        //stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("ServiceTag", "onCreate: Service Stop")
        super.onDestroy()
    }

}
