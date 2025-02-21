package com.services.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.services.MainActivity
import com.services.R
import kotlin.concurrent.thread

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

        startLoggerForegroundService()
        //stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("ServiceTag", "onCreate: Service Stop")
        super.onDestroy()
    }


    private fun startLoggerForegroundService(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel()
            val notification = createNotification()
            startForeground(1, notification)
            Log.d("ServiceTag", "startForeground: Service is Running")
        }
        else{
            startForeground(1, Notification())
        }
    }

    // notification for foreground service
    private fun getPendingIntent(): PendingIntent{
        val notificationIntent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private val channelId = "notification_channel_id"
    private val channelName = "Notification Channel"
    private val channelDescription = "Description of the notification channel"
    private val importance = NotificationManager.IMPORTANCE_DEFAULT

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(): NotificationChannel{
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }
        // Register the channel with the system
        /*val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)*/
        val notificationManager: NotificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        return channel
    }

    private fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("Service is running")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_pokemon_24)
            .setOngoing(true)
            .build()
        return notification
    }

}
